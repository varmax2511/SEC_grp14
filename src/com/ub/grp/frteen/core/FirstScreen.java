package com.ub.grp.frteen.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
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
   * @param *          !null.Configuration
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
	
    final JLabel num_members = new JLabel("Number of group members");
    num_members.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    // number of members supported
    final String[] numberOfMemberCandidates = new String[]{"2", "3", "4", "5",
        "6", "7"};
    numMemberBox = new JComboBox<>(numberOfMemberCandidates);
    numMemberBox.addActionListener(this);
    
    final JPanel tablePanel = new JPanel();
    this.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));
    
    final GridLayout layout = new GridLayout(2, 2);
    
    JPanel obj = this;
    
    layout.setHgap(config.getHorizontalPadding());
    layout.setVgap(config.getVerticalPadding());
    final JLabel checkLabel = new JLabel("Were scores previously entered?");
    
    
    checkLabel.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    
    optionBox = new JComboBox<>(new String[]{"Yes", "No"});
    optionBox.addActionListener(this);
    
    nextButton = new JButton("Next");
    nextButton.addActionListener(this);

   
    nextButton.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    final JLabel headline = new JLabel("WELCOME");
    headline.setFont(new Font(AppConstants.DEFAULT_FONT, Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT); 
    
    tablePanel.add(checkLabel);
    tablePanel.add(optionBox);
    tablePanel.setLayout(layout);
    tablePanel.add(num_members);
    tablePanel.add(numMemberBox);

    // add components vertically
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.add(headline);
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
      prevEnter = optionBox.getSelectedItem().toString().toLowerCase().equals("yes");
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

  /**
   * sets the visibility of current screen.
   * @param value true--> on. false --> off
   */
  private void setVisibilityOfScreen(boolean value) {
    this.setVisible(value);
  }//

  /**
   * Configuration class for {@link FirstScreen}
   *  accepts surveyscreen object. and other UI utilities configuration with pre-defined parameters
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

    /**
     *  sets the next JFrame objects to proceed to survey screen
     * @param window
     */
    public void setNextJFrameObject(SurveyScreen window) {
      surveyScreen = window;
    }

    /**
     * gets the number of necessary padding in horizontal direction
     * @return
     */
    public int getHorizontalPadding() {
      return horizontalPadding;
    }

    /**
     * sets the number of padding in horizontal direction.
     * @param horizontalPadding
     */
    public void setHorizontalPadding(int horizontalPadding) {
      this.horizontalPadding = horizontalPadding;
    }

    /**
     * gets the number of necessary padding in vertical direction
     * @return
     */
    public int getVerticalPadding() {
      return verticalPadding;
    }

    /**
     * sets the number of padding in the vertical direction
     * @param verticalPadding
     */
    public void setVerticalPadding(int verticalPadding) {
      this.verticalPadding = verticalPadding;
    }

    /**
     *
     * @return the survey screen class object
     */
    public SurveyScreen getSurveyScreen() {
      return surveyScreen;
    }

    /**
     * sets the survey screen class object
     * @param surveyScreen
     */
    public void setSurveyScreen(SurveyScreen surveyScreen) {
      this.surveyScreen = surveyScreen;
    }

    /**
     *
     * @return the number of current members
     */
    public int getNumberOfMembers() {
      return numberOfMembers;
    }

    /**
     * sets the number of current members
     * @param numberOfMembers
     */
    public void setNumberOfMembers(int numberOfMembers) {
      this.numberOfMembers = numberOfMembers;
    }

    /**
     *
     * @return the JFrame application container
     */
    public JFrame getAppContainer() {
      return appContainer;
    }

  }
}//
