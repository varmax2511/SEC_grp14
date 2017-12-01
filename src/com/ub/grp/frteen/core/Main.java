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
    FontUtils.setUIFont(
        new javax.swing.plaf.FontUIResource(AppConstants.FONT_SERIF, Font.PLAIN, 20));

    appContainer.setLocation(500, 200);
    // appContainer.setSize(1000, 1000);
    // appContainer.add(new LoginScreen().getScreen());

    appContainer.setLayout(new BorderLayout(10, 10));
    final List<String> groupMembers = new ArrayList<>();
    groupMembers.add("Varun");
    groupMembers.add("Hiro");
    groupMembers.add("Ralph");
    groupMembers.add("Nick");
    groupMembers.add("Shivam");

    final SurveyScreen.Config surveyConfig = new SurveyScreen.Config(
        groupMembers);
    containerPanel.add(new SurveyScreen(surveyConfig).getScreen(),
        BorderLayout.CENTER);

    appContainer.add(containerPanel);
    appContainer.setVisible(true);
    appContainer.pack();

  }
}
