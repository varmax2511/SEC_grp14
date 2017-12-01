package com.ub.grp.frteen.core;

import javafx.scene.control.CheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstScreen extends JPanel implements AppScreen, ActionListener{

    private boolean prevEnter = false;
    private int numberOfMembers=0;
    private JComboBox<String> check;
    private JComboBox<String> numMemberBox;
    private JButton OK;
    private JPanel nextScreen=null;
    JFrame appContainer=null;
    private SurveyScreen surveyScreen=null;

    @Override
    public JPanel getScreen(){
        final JLabel num_members = new JLabel("The number of menbers");
        String[] num_member_candidates = new String[]{"2","3","4","5","6","7"};
        numMemberBox = new JComboBox<>(num_member_candidates);
        numMemberBox.addActionListener(this);
        final GridLayout layout = new GridLayout(3,3);
        final JLabel checkLabel = new JLabel("Previously Entered");
        check = new JComboBox<>(new String[]{"YES","NO"});
        check.addActionListener(this);
        OK = new JButton("Next");
        OK.addActionListener(this);
        this.add(checkLabel);
        this.add(check);
        this.setLayout(layout);

        this.add(num_members);
        this.add(numMemberBox);
        this.add(OK);
        this.setVisible(true);
        return this;
    }//

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == numMemberBox||e.getSource()==OK){
            numberOfMembers = Integer.parseInt(numMemberBox.getSelectedItem().toString());
        }
        if(e.getSource()==check || e.getSource()==OK){
            prevEnter = check.getSelectedItem().toString().equals("YES");
        }
        if(e.getSource() == OK){
            setVisibilityOfScreen(false);
            if(surveyScreen!=null) {
                //remove groupmembers
                surveyScreen.getConfig().setMembers(numberOfMembers);

                surveyScreen.getConfig().setRandomScore(prevEnter);
                nextScreen= surveyScreen.getScreen();
                appContainer.add(nextScreen);
                nextScreen.setVisible(true);
                appContainer.pack();
            }
        }
    }//

    private void setVisibilityOfScreen(boolean val){
        this.setVisible(val);
    }//

    public void setNextScreen(JPanel ob){
        nextScreen = ob;
        nextScreen.setVisible(false);
    }

    public void setNextJFrameObject(SurveyScreen window){
        surveyScreen= window;
    }

    public void setAppContainer(JFrame window){
        appContainer= window;
    }//

}//
