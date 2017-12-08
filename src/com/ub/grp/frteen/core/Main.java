package com.ub.grp.frteen.core;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.ub.grp.frteen.utils.AppConstants;
import com.ub.grp.frteen.utils.FontUtils;
/**
 * Starter class
 *
 * @author varunjai
 *
 */
public class Main {

  public static void main(String[] args) {

    final JFrame appContainer = new JFrame();
    final JPanel containerPanel = new JPanel();
    // add padding
    final Border padding = BorderFactory.createEmptyBorder(10, 40, 10, 40);
    containerPanel.setBorder(padding);

    // set application font
    FontUtils.setUIFont(new javax.swing.plaf.FontUIResource(
        AppConstants.FONT_SERIF, Font.PLAIN, 20));

    // group members default list
    final List<String> groupMembers = new ArrayList<>();
    appContainer.setLayout(new BorderLayout(10, 10));
    groupMembers.add("Varun");
    groupMembers.add("Hiro");
    groupMembers.add("Ralph");
    groupMembers.add("Nick");
    groupMembers.add("Shivam");
    groupMembers.add("Fred");
    groupMembers.add("Johnny");

    // configure Survey Screen
    final SurveyScreen surveyWindow = new SurveyScreen(
        new SurveyScreen.Config(groupMembers));

    // configure First screen
    final FirstScreen.Config firstScreenConfig = new FirstScreen.Config(
        appContainer);
    firstScreenConfig.setNextJFrameObject(surveyWindow);
    final FirstScreen firstScreen = new FirstScreen(firstScreenConfig);

    // configure the container
    appContainer.add(firstScreen.getScreen());
    appContainer.pack();
    appContainer.setTitle("Student Peer Evaluation System");
    appContainer.setLocation(500, 200);
    appContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    appContainer.setVisible(true);

  }
}
