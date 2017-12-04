package com.ub.grp.frteen.core;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
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
    FontUtils.setUIFont(
        new javax.swing.plaf.FontUIResource(AppConstants.FONT_SERIF, Font.PLAIN, 20));

    appContainer.setLocation(500, 200);
    appContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // appContainer.add(new LoginScreen().getScreen());

      FirstScreen firstScreen = new FirstScreen();
        firstScreen.setAppContainer(appContainer);
      //appContainer.add(firstScreen);

    List<String> groupMembers = new ArrayList<>();
    appContainer.setLayout(new BorderLayout(10, 10));
    groupMembers.add("Varun");
    groupMembers.add("Hiro");
    groupMembers.add("Ralph");
    groupMembers.add("Nick");
    groupMembers.add("Shivam");
    groupMembers.add("Fred");
    groupMembers.add("Johnny");

    SurveyScreen.Config surveyConfig = new SurveyScreen.Config(groupMembers);

      SurveyScreen surveyWindow = new SurveyScreen(surveyConfig);
      firstScreen.setNextJFrameObject(surveyWindow);

      appContainer.add(firstScreen.getScreen());
    //containerPanel.add(new SurveyScreen(surveyConfig).getScreen(),BorderLayout.CENTER);

    //appContainer.add(containerPanel);
    appContainer.pack();
    appContainer.setVisible(true);
  }
}
