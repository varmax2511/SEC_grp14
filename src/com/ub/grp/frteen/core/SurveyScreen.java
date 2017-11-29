package com.ub.grp.frteen.core;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This screen represents the Survey form filled by the student.
 *
 * @author varunjai
 *
 */
public class SurveyScreen extends JPanel implements AppScreen<JPanel>, ActionListener {

  private static final String DEFAULT_SERIF = "Serif";
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private final Config config;
  
  // A list of score selectors for each group member
  private HashMap<String, ArrayList<JComboBox<Integer>>> scoreSelectors;

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
    this.scoreSelectors = new HashMap<String, ArrayList<JComboBox<Integer>>>();
  }

  @Override
  public JPanel getScreen() {

    // add components vertically
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    final JPanel tablePanel = new JPanel();
    final GridLayout layout = new GridLayout(
        config.getGroupMembers().size() + 2, config.getNumCols());
    tablePanel.setLayout(layout);

    getHeader(tablePanel);

    // create the form
    for (final String member : config.getGroupMembers()) {
    	  scoreSelectors.put(member, new ArrayList<JComboBox<Integer>>());
      tablePanel.add(new JLabel(member));

      for (int i = 1; i < config.getNumCols(); i++) {
        final JComboBox<Integer> scoreSelector = new JComboBox<>();
        for (int j = config.getLowestScore(); j <= config
            .getHighestScore(); j++) {
          scoreSelector.addItem(j);
        } // for
        tablePanel.add(scoreSelector);
        scoreSelectors.get(member).add(scoreSelector);
      } // for

    } // for

    final JPanel buttonPanel = new JPanel();
    final JButton submit = new JButton("Submit");
    final JButton reset = new JButton("Reset");
    submit.addActionListener(this);
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

    final JLabel headline = new JLabel("Survey Form");
    headline.setFont(new Font(DEFAULT_SERIF, Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.add(headline);

    tablePanel.add(new JLabel("Member"));
    tablePanel.add(new JLabel("Professionalism"));
    tablePanel.add(new JLabel("Participation"));
    tablePanel.add(new JLabel("Work Evaluation"));
    
  }
  
  	@Override
	public void actionPerformed(ActionEvent e) {
  		Integer totalPoints = 0;
  		HashMap<String, Integer> memberScores = new HashMap<String, Integer>();
		for (String member : config.getGroupMembers()) {
			ArrayList<JComboBox<Integer>> comboBoxes = this.scoreSelectors.get(member);
			Integer score = 0;
			for (JComboBox<Integer> comboBox : comboBoxes) {
				score += (Integer)comboBox.getSelectedItem();
			}
			memberScores.put(member, score);
			totalPoints += score;
		  }
		  String outputString = "";
		  for (String member : config.getGroupMembers()) {
			  double normalized = (double) memberScores.get(member) / totalPoints;
			  outputString += String.format("%s's normalized score: %.4f\n", member, normalized);
		  }
		  JOptionPane.showMessageDialog(null, outputString);
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
    private int numCols = 4;
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

  }

	
}
