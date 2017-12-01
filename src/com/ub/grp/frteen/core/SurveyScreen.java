package com.ub.grp.frteen.core;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

  private static final String DEFAULT_SERIF = "Serif";
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
        config.getDisplayMembers().size() + 1, config.getNumCols());
    tablePanel.setLayout(layout);

    getHeader(tablePanel);

    // create the form
    for (final String member : config.getDisplayMembers()) {
      tablePanel.add(new JLabel(member));

      for (int i = 1; i < config.getNumCols(); i++) {
        final JComboBox<Integer> scoreSelector = new JComboBox<>();
        for (int j = config.getLowestScore(); j <= config
            .getHighestScore(); j++) {
          if(!config.getRandomScore())scoreSelector.addItem(j);
          else scoreSelector.addItem(getRandomVal());
        } // for

        tablePanel.add(scoreSelector);
      } // for

    } // for

    final JPanel buttonPanel = new JPanel();
    final JButton submit = new JButton("Submit");
    final JButton reset = new JButton("Reset");
    buttonPanel.add(submit);
    buttonPanel.add(reset);

    this.add(tablePanel);
    this.add(buttonPanel);

    return this;
  }

  public int getRandomVal(){
    Random random = new Random();
    return random.nextInt(5)+1;
  }

  public Config getConfig(){return this.config;}

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

  /**
   * Configuration class for {@link SurveyScreen}
   *
   * @author varunjai
   *
   */
  public static class Config {

    private final List<String> groupMembers;
    private List<String> displayMembers;
    private int lowestScore = 1;
    private int highestScore = 5;
    private int numCols = 4;
    private boolean randomScore=false;

    /**
     *
     * @param groupMembers
     *          !empty
     */
    public Config(List<String> groupMembers) {
      this.groupMembers = groupMembers;
      this.displayMembers= new LinkedList<>();
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
    public List<String> getDisplayMembers(){return displayMembers;}

    public int getNumCols() {
      return numCols;
    }

    public void setMembers(int num){
      for(int i=0;i<num&&i<groupMembers.size();i++){
        displayMembers.add(groupMembers.get(i));
      }
    }

    public void setRandomScore(boolean val){randomScore = val;}
    public boolean getRandomScore(){return randomScore;}

  }
}
