package com.ub.grp.frteen.core;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * This class represents the Login Screen for the application. The user shall be
 * directed to the login screen on first time access.
 * 
 * @author varunjai
 *
 */
public class LoginScreen extends JPanel implements AppScreen<JPanel> {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   *
   * @return
   */
  @Override
  public JPanel getScreen() {
    final JLabel userName = new JLabel("Username");
    final JLabel passWord = new JLabel("Password");
    final JTextField userNameTxt = new JTextField();
    final JTextField passWordTxt = new JTextField();
    final JButton submit = new JButton("Submit");
    final JButton reset = new JButton("Reset");

    // Grid Layout
    final GridLayout layout = new GridLayout(3, 3);
    this.setLayout(layout);

    // add components
    this.add(userName);
    this.add(userNameTxt);
    this.add(passWord);
    this.add(passWordTxt);
    this.add(submit);
    this.add(reset);
    
    // set visibility
    this.setVisible(true);
    return this;
  }

}
