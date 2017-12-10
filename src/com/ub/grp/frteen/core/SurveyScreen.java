package com.ub.grp.frteen.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.ub.grp.frteen.utils.AppConstants;
import com.ub.grp.frteen.utils.FontUtils;
import com.ub.grp.frteen.utils.NormalizationUtil;

/**
 * This screen represents the Survey form filled by the student.
 *
 * @author varunjai
 *
 */
public class SurveyScreen extends JPanel
    implements
      AppScreen<JPanel>,
      ActionListener {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private final Config config;

  // A list of score selectors for each group member
  private Map<String, ArrayList<JComboBox<Integer>>> scoreSelectors;

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
    this.scoreSelectors = new LinkedHashMap<String, ArrayList<JComboBox<Integer>>>();
  }

  @Override
  public JPanel getScreen() {

    // add components vertically
	JPanel obj = this;
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    final JPanel tablePanel = new JPanel();
    // setup layout
    final GridLayout layout = new GridLayout(
        config.getDisplayMembers().size() + 1, config.getNumCols());
    layout.setHgap(config.getHorizontalPadding());
    layout.setVgap(config.getVerticalPadding());

    tablePanel.setLayout(layout);
    final JLabel headline = new JLabel("SURVEY FORM");
    headline.setFont(new Font(AppConstants.DEFAULT_FONT, Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT);
    //getHeader(tablePanel);
    final JLabel memberss = new JLabel(AppConstants.MEMBER_TXT);
    memberss.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    final JLabel prof = new JLabel(AppConstants.PROFESSIONALISM_TXT);
    prof.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    final JLabel part = new JLabel(AppConstants.PARTICIPATION_TXT);
    part.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    final JLabel work = new JLabel(AppConstants.WORK_EVALUATION_TXT);
    work.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    // create the form
    
    JLabel mem[] = new JLabel[config.getDisplayMembers().size()];
    for(int i=0;i<mem.length;i++)
    	mem[i] = new JLabel(" ");
    int k = 0;
    final JComboBox<Integer>[][] scoreSelector = new JComboBox[config.getDisplayMembers().size()][config.getNumCols()-1];
    for(int i = 0; i < config.getDisplayMembers().size(); i++)
    	for(int j = 0; j < config.getNumCols()-1; j++)
    	scoreSelector[i][j] = new JComboBox<Integer>();
    for (final String member : config.getDisplayMembers()) {   	
      scoreSelectors.put(member, new ArrayList<JComboBox<Integer>>());
      mem[k].setText(member); /*= new JLabel(member);*/
      mem[k].setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
     /* tablePanel.add(mem[k]);*/

      // add columns
      for (int i = 0; i < config.getNumCols()-1; i++) {
        
        // add drop down items
        for (int j = config.getLowestScore(); j <= config.getHighestScore(); j++) {
        		scoreSelector[k][i].addItem(j);
        } // for
        if (config.getRandomScore()) {
        		scoreSelector[k][i].setSelectedIndex(getRandomVal());
        }
        scoreSelectors.get(member).add(scoreSelector[k][i]);
      } // for
      k++;
    } // for

    // add buttons
    final JPanel buttonPanel = new JPanel();
    final JButton submit = new JButton("Submit");
    
    submit.addActionListener(this);
    submit.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    submit.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.add(headline);
    tablePanel.add(memberss);
    tablePanel.add(prof);
    tablePanel.add(part);
    tablePanel.add(work);
    for(int l=0; l<config.getDisplayMembers().size();l++) {
    	tablePanel.add(mem[l]);
    for(int n = 0; n<config.getNumCols()-1;n++)
        tablePanel.add(scoreSelector[l][n]);}
    buttonPanel.add(submit);
    
    this.add(tablePanel);
    // adding spacing
    this.add(new JLabel(" "));
    this.add(buttonPanel);

    final Border padding = BorderFactory.createEmptyBorder(10, 40, 10, 40);
    this.setBorder(padding);
    return this;
  }

  /**
   * Get the random value for score
   * @return random integer value
   */
  public int getRandomVal() {
    Random random = new Random();
    return random.nextInt(5) + 1;
  }

  /**
   *
   * @return config class object
   */
  public Config getConfig() {
    return this.config;
  }

  /**
   * Get the header of the form table.
   */
  private void getHeader(JPanel tablePanel) {

    this.add(FontUtils.getHeadline(AppConstants.SURVEY_FORM_TEXT));
    final JLabel member = new JLabel(AppConstants.MEMBER_TXT);
    member.setForeground(Color.white);
    member.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    final JLabel prof = new JLabel(AppConstants.PROFESSIONALISM_TXT);
    prof.setForeground(Color.white);
    prof.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    final JLabel part = new JLabel(AppConstants.PARTICIPATION_TXT);
    part.setForeground(Color.white);
    part.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    final JLabel work = new JLabel(AppConstants.WORK_EVALUATION_TXT);
    work.setForeground(Color.white);
    work.setFont(new Font(AppConstants.DEFAULT_FONT,Font.BOLD, 20));
    tablePanel.add(member);
    tablePanel.add(prof);
    tablePanel.add(part);
    tablePanel.add(work);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    final HashMap<String, Integer> memberScores = new LinkedHashMap<>();

    for (final String member : config.getDisplayMembers()) {

      if(this.scoreSelectors.get(member) == null){
        continue;
      }
      
      final ArrayList<JComboBox<Integer>> comboBoxes = this.scoreSelectors
          .get(member);
      Integer score = 0;

      for (final JComboBox<Integer> comboBox : comboBoxes) {
        score += (Integer) comboBox.getSelectedItem();
      } // for
      memberScores.put(member, score);
    } // for

    final StringBuilder outputString = new StringBuilder();
    for (final Entry<String, Double> member2Normalized : NormalizationUtil
        .getNormalizedScores(memberScores).entrySet()) {
      outputString.append(String.format("%s's normalized score: %.4f\n",
          member2Normalized.getKey(), member2Normalized.getValue()));
    } // for

    JOptionPane.showMessageDialog(null, outputString.toString());
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
    private boolean randomScore = false;

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
      this.displayMembers = new LinkedList<>();
    }

    /**
     *
     * @return the default lowest score
     */
    public int getLowestScore() {
      return lowestScore;
    }

    /**
     * sets the lowest score
     * @param lowestScore
     */
    public void setLowestScore(int lowestScore) {
      this.lowestScore = lowestScore;
    }

    /**
     *
     * @return the default highest score
     */
    public int getHighestScore() {
      return highestScore;
    }

    /**
     * sets the highest score
     * @param highestScore
     */
    public void setHighestScore(int highestScore) {
      this.highestScore = highestScore;
    }

    /**
     *
     * @return the list of members that were set in main
     */
    public List<String> getGroupMembers() {
      return groupMembers;
    }

    /**
     *
     * @return the selected members by first screen
     */
    public List<String> getDisplayMembers() {
      return displayMembers;
    }

    /**
     * gets the number of columns for UI adjustment
     * @return
     */
    public int getNumCols() {
      return numCols;
    }

    /**
     * sets the members for displaying on peer-evaluation window
     * @param num
     */
    public void setMembers(int num) {
      for (int i = 0; i < num && i < groupMembers.size(); i++) {
        displayMembers.add(groupMembers.get(i));
      }
    }

    /**
     * a checker sets the random score or not (depending on the choice of the first screen)
     * @param val
     */
    public void setRandomScore(boolean val) {
      randomScore = val;
    }

    /**
     *  check gets random score or not when displaying evaluation scores
     * @return
     */
    public boolean getRandomScore() {
      return randomScore;
    }

    /**
     *
     * @return the number of necessary padding in horizontal line
     */
    public int getHorizontalPadding() {
      return horizontalPadding;
    }

    /**
     * sets the needed number of padding in horizontal line
     * @param horizontalPadding
     */
    public void setHorizontalPadding(int horizontalPadding) {
      this.horizontalPadding = horizontalPadding;
    }

    /**
     * gets the number of padding in vertical line
     * @return
     */
    public int getVerticalPadding() {
      return verticalPadding;
    }

    /**
     * sets the number of padding in vertical line
     * @param verticalPadding
     */
    public void setVerticalPadding(int verticalPadding) {
      this.verticalPadding = verticalPadding;
    }

  }

}
