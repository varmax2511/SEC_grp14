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
    final GridLayout layout = new GridLayout(
        config.getGroupMembers().size() + 2, config.getGroupMembers().size());
    tablePanel.setLayout(layout);

    getHeader(tablePanel);

    // create the form
    for (final String member : config.getGroupMembers()) {
      tablePanel.add(new JLabel(member));

      for (int i = 1; i < config.getGroupMembers().size(); i++) {
        final JComboBox<Integer> scoreSelector = new JComboBox<>();
        for (int j = config.getLowestScore(); j <= config
            .getHighestScore(); j++) {
          scoreSelector.addItem(j);
        } // for

        tablePanel.add(scoreSelector);
      } // for

    } // for

    JPanel buttonPanel = new JPanel();
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

    JLabel headline = new JLabel("Survey Form"); 
    headline.setFont(new Font("Serif", Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    this.add(headline);
    
    for (int i = 0; i < config.getGroupMembers().size(); i++) {
      // print
      if (i == 0) {
        tablePanel.add(new JLabel("Member"));
        continue;
      }

      tablePanel.add(new JLabel("Member " + (i)));
    }
  }

  /**
   * Configuration class for {@link SurveyScreen}
   *
   * @author varunjai
   *
   */
  public static class Config {

    private final List<String> groupMembers;
    private int lowestScore = 1;
    private int highestScore = 5;

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

  }
}
