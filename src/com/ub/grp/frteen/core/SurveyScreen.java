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
import javax.swing.JToggleButton;
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
    headline.setFont(new Font(AppConstants.DEFAULT_SERIF, Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT);
    //getHeader(tablePanel);
    final JLabel memberss = new JLabel(AppConstants.MEMBER_TXT);
    memberss.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    final JLabel prof = new JLabel(AppConstants.PROFESSIONALISM_TXT);
    prof.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    final JLabel part = new JLabel(AppConstants.PARTICIPATION_TXT);
    part.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    final JLabel work = new JLabel(AppConstants.WORK_EVALUATION_TXT);
    work.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
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
      mem[k].setFont(new Font("Comic Sans MS",Font.BOLD, 20));
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
    final JButton reset = new JButton("Reset");
    submit.addActionListener(this);
    submit.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    submit.setAlignmentX(Component.CENTER_ALIGNMENT);
    reset.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    reset.setAlignmentX(Component.CENTER_ALIGNMENT);
   
    JToggleButton night = new JToggleButton("Night Mode");
    night.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    night.setAlignmentX(Component.CENTER_ALIGNMENT);
    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent itemEvent) {
          int state = itemEvent.getStateChange();
          if (state == ItemEvent.SELECTED) {
        	    headline.setForeground(Color.white);
        	    memberss.setForeground(Color.white);
        	    prof.setForeground(Color.white);
        	    part.setForeground(Color.white);
        	    work.setForeground(Color.white);

        	  	night.setBackground(Color.BLACK);
        	    night.setForeground(Color.WHITE);
          
          		obj.setBackground(Color.BLACK);
          		
          	    tablePanel.setBackground(Color.BLACK);
          	    
          	    for(int l=0; l<config.getDisplayMembers().size();l++) {
                mem[l].setForeground(Color.white);
          	    
          	    for(int n = 0; n<config.getNumCols()-1;n++) {
          	    scoreSelector[l][n].setForeground(Color.WHITE);
                scoreSelector[l][n].setBackground(Color.BLACK);}}
          	    
          	    buttonPanel.setBackground(Color.BLACK);
          	    
          	    submit.setBackground(Color.BLACK);
                submit.setForeground(Color.WHITE);
                
                reset.setBackground(Color.BLACK);
                reset.setForeground(Color.WHITE);
                
                repaint();
                
          }	
          else {
        	headline.setForeground(Color.black);  
        	memberss.setForeground(Color.black);
      	    prof.setForeground(Color.black);
      	    part.setForeground(Color.black);
      	    work.setForeground(Color.black);

      	  	night.setBackground(Color.white);
      	    night.setForeground(Color.black);
        
        		obj.setBackground(Color.white);
        		
        	    tablePanel.setBackground(Color.white);
        	    
        	    for(int l=0; l<config.getDisplayMembers().size();l++) {
              mem[l].setForeground(Color.black);
        	    
        	    for(int n = 0; n<config.getNumCols()-1;n++) {
        	    scoreSelector[l][n].setForeground(Color.black);
              scoreSelector[l][n].setBackground(Color.white);}}
        	    
        	    buttonPanel.setBackground(Color.white);
        	    
        	    submit.setBackground(Color.white);
              submit.setForeground(Color.black);
              
              reset.setBackground(Color.white);
              reset.setForeground(Color.black);
        	  repaint();
          }  
        }};
    night.addItemListener(itemListener);
    this.add(night);
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
    buttonPanel.add(reset);
    this.add(tablePanel);
    // adding spacing
    this.add(new JLabel(" "));
    this.add(buttonPanel);

    final Border padding = BorderFactory.createEmptyBorder(10, 40, 10, 40);
    this.setBorder(padding);
    return this;
  }

  public int getRandomVal() {
    Random random = new Random();
    return random.nextInt(5) + 1;
  }

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
    member.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    final JLabel prof = new JLabel(AppConstants.PROFESSIONALISM_TXT);
    prof.setForeground(Color.white);
    prof.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    final JLabel part = new JLabel(AppConstants.PARTICIPATION_TXT);
    part.setForeground(Color.white);
    part.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
    final JLabel work = new JLabel(AppConstants.WORK_EVALUATION_TXT);
    work.setForeground(Color.white);
    work.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
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
    public List<String> getDisplayMembers() {
      return displayMembers;
    }

    public int getNumCols() {
      return numCols;
    }

    public void setMembers(int num) {
      for (int i = 0; i < num && i < groupMembers.size(); i++) {
        displayMembers.add(groupMembers.get(i));
      }
    }

    public void setRandomScore(boolean val) {
      randomScore = val;
    }
    public boolean getRandomScore() {
      return randomScore;
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
