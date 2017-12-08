package com.ub.grp.frteen.core;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.ub.grp.frteen.utils.AppConstants;
import com.ub.grp.frteen.utils.FontUtils;

/**
 * This {@link AppScreen} returns the First screen displayed to the user where
 * they are required to enter the number of members and if they have previously
 * entered the scores.
 *
 */
public class FirstScreen extends JPanel
    implements
      AppScreen<JPanel>,
      ActionListener {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private final Config config;
  private JComboBox<String> optionBox;
  private JComboBox<String> numMemberBox;
  private JButton nextButton;

  /**
   *
   * @param config
   *          !null.Configuration
   */
  public FirstScreen(Config config) {
    // validate
    if (config == null) {
      throw new IllegalArgumentException("Configuration cannot be null");
    }

    this.config = config;
  }

  @Override
  public JPanel getScreen() {

    final JLabel num_members = new JLabel("Number of group menbers");
    // number of members supported
    final String[] numberOfMemberCandidates = new String[]{"2", "3", "4", "5",
        "6", "7"};
    numMemberBox = new JComboBox<>(numberOfMemberCandidates);
    numMemberBox.addActionListener(this);

    final JPanel tablePanel = new JPanel();
    final GridLayout layout = new GridLayout(2, 2);
    layout.setHgap(config.getHorizontalPadding());
    layout.setVgap(config.getVerticalPadding());
    final JLabel checkLabel = new JLabel("Were scores previously entered?");
    optionBox = new JComboBox<>(new String[]{"YES", "NO"});
    optionBox.addActionListener(this);
    nextButton = new JButton("Next");
    nextButton.addActionListener(this);
    tablePanel.add(checkLabel);
    tablePanel.add(optionBox);
    tablePanel.setLayout(layout);

    tablePanel.add(num_members);
    tablePanel.add(numMemberBox);

    // add components vertically
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.add(FontUtils.getHeadline("Welcome!"));
    this.add(tablePanel);
    this.add(new JLabel(" "));
    this.add(nextButton);
    final Border padding = BorderFactory.createEmptyBorder(10, 40, 10, 40);
    this.setBorder(padding);

    this.setVisible(true);
    return this;
  }//

  @Override
  public void actionPerformed(ActionEvent e) {
    int numberOfMembers = 0;
    if (e.getSource() == numMemberBox || e.getSource() == nextButton) {
      numberOfMembers = Integer
          .parseInt(numMemberBox.getSelectedItem().toString());
    }

    boolean prevEnter = false;
    if (e.getSource() == optionBox || e.getSource() == nextButton) {
      prevEnter = optionBox.getSelectedItem().toString().equals("YES");
    }
    if (e.getSource() == nextButton) {
      setVisibilityOfScreen(false);
      if (config.getSurveyScreen() != null) {
        // remove groupmembers
        config.getSurveyScreen().getConfig().setMembers(numberOfMembers);

        config.getSurveyScreen().getConfig().setRandomScore(prevEnter);
        final JPanel nextScreen = config.getSurveyScreen().getScreen();
        config.getAppContainer().add(nextScreen);
        nextScreen.setVisible(true);
        config.getAppContainer().pack();
      }
    }
  }//

  private void setVisibilityOfScreen(boolean value) {
    this.setVisible(value);
  }//

  /**
   * Configuration class for {@link FirstScreen}
   *
   *
   */
  public static class Config {

    private SurveyScreen surveyScreen;
    private int horizontalPadding = 30;
    private int verticalPadding = 10;
    private final JFrame appContainer;
    int numberOfMembers = 0;

    /**
     *
     * @param appContainer
     *          !null. Parent container for the screen.
     */
    public Config(JFrame appContainer) {
      // validate
      if (appContainer == null) {
        throw new IllegalArgumentException("Configuration cannot be null");
      }

      this.appContainer = appContainer;
    }
    public void setNextJFrameObject(SurveyScreen window) {
      surveyScreen = window;
    }

    public int getHorizontalPadding() {
      return horizontalPadding;
    }

    public void setHorizontalPadding(int horizontalPadding) {
      this.horizontalPadding = horizontalPadding;
    }

    public int getVerticalPadding() {
      return verticalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
      this.verticalPadding = verticalPadding;
    }

    public SurveyScreen getSurveyScreen() {
      return surveyScreen;
    }

    public void setSurveyScreen(SurveyScreen surveyScreen) {
      this.surveyScreen = surveyScreen;
    }

    public int getNumberOfMembers() {
      return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
      this.numberOfMembers = numberOfMembers;
    }

    public JFrame getAppContainer() {
      return appContainer;
    }

  }
}//
