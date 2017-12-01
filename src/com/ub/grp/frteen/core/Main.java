package com.ub.grp.frteen.core;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

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
    // set application font
    FontUtils.setUIFont(
        new javax.swing.plaf.FontUIResource("Serif", Font.PLAIN, 20));
    
    appContainer.setLocation(500, 200);
    //appContainer.setSize(1000, 1000);
    //appContainer.add(new LoginScreen().getScreen());

      FirstScreen firstScreen = new FirstScreen();
        firstScreen.setAppContainer(appContainer);
      //appContainer.add(firstScreen);

    List<String> groupMembers = new ArrayList<>();
    groupMembers.add("Varun");
    groupMembers.add("Hiro");
    groupMembers.add("Ralph");
    groupMembers.add("Nick");
    groupMembers.add("Shivam");

    SurveyScreen.Config surveyConfig = new SurveyScreen.Config(groupMembers);

      SurveyScreen surveyWindow = new SurveyScreen(surveyConfig);
      firstScreen.setNextJFrameObject(surveyWindow);

      appContainer.add(firstScreen.getScreen());

    appContainer.setVisible(true);
    appContainer.pack();

  }
}
