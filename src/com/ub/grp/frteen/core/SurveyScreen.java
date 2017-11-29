package com.ub.grp.frteen.core;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ub.grp.frteen.utils.AppConstants;

/**
 * This screen represents the Survey form filled by the student.
 *
 * @author varunjai
 *
 */
public class SurveyScreen extends JPanel implements AppScreen<JPanel> {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private final Config config;

  /**
   * Initialize.
   *
   * @param config
   *          !null
   */
  public SurveyScreen(Config config) {
    // null check
    if (config == null) {
      throw new IllegalArgumentException("Configuration object cannot be null");
    }
    this.config = config;
  }

  @Override
  public JPanel getScreen() {

    // add components vertically
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    final JPanel tablePanel = new JPanel();
    // setup layout
    final GridLayout layout = new GridLayout(
        config.getGroupMembers().size() + 2, config.getNumCols());
    layout.setHgap(config.getHorizontalPadding());
    layout.setVgap(config.getVerticalPadding());

    tablePanel.setLayout(layout);

    getHeader(tablePanel);

    // create the form
    for (final String member : config.getGroupMembers()) {
      tablePanel.add(new JLabel(member));

      // add columns
      for (int i = 1; i < config.getNumCols(); i++) {
        final JComboBox<Integer> scoreSelector = new JComboBox<>();

        // add drop down items
        for (int j = config.getLowestScore(); j <= config
            .getHighestScore(); j++) {
          scoreSelector.addItem(j);
        } // for

        tablePanel.add(scoreSelector);
      } // for

    } // for

    // add buttons
    final JPanel buttonPanel = new JPanel();
    final JButton submit = new JButton("Submit");
    final JButton reset = new JButton("Reset");
    buttonPanel.add(submit);
    buttonPanel.add(reset);

    this.add(tablePanel);
    this.add(buttonPanel);

    return this;
  }

  /**
   * Get the header of the form table.
   */
  private void getHeader(JPanel tablePanel) {

    final JLabel headline = new JLabel(AppConstants.SURVEY_FORM_TEXT);
    headline.setFont(new Font(AppConstants.DEFAULT_SERIF, Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.add(headline);

    tablePanel.add(new JLabel(AppConstants.MEMBER_TXT));
    tablePanel.add(new JLabel(AppConstants.PROFESSIONALISM_TXT));
    tablePanel.add(new JLabel(AppConstants.PARTICIPATION_TXT));
    tablePanel.add(new JLabel(AppConstants.WORK_EVALUATION_TXT));

  }

  /**
   * Configuration class for {@link SurveyScreen}
   *
   * @author varunjai
   *
   */
  public static class Config {

    private final List<String> groupMembers;
    private int lowestScore = 0;
    private int highestScore = 5;
    private int numCols = 4;
    private int horizontalPadding = 30;
    private int verticalPadding = 10;
    /**
     *
     * @param groupMembers
     *          !empty
     */
    public Config(List<String> groupMembers) {
      this.groupMembers = groupMembers;
    }

    public int getLowestScore() {
      return lowestScore;
    }

    public void setLowestScore(int lowestScore) {
      this.lowestScore = lowestScore;
    }

    public int getHighestScore() {
      return highestScore;
    }

    public void setHighestScore(int highestScore) {
      this.highestScore = highestScore;
    }

    public List<String> getGroupMembers() {
      return groupMembers;
    }

    public int getNumCols() {
      return numCols;
    }

    public void setNumCols(int numCols) {
      this.numCols = numCols;
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

  }
}
