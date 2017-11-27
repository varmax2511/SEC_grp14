package com.ub.grp.frteen.utils;

import java.awt.Font;

import javax.swing.UIManager;

public class FontUtils {

  public static Font getDefaultFont() {
    return new Font(null, Font.PLAIN, 15);
  }
  
  public static void setUIFont (javax.swing.plaf.FontUIResource f){
    java.util.Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get (key);
      if (value instanceof javax.swing.plaf.FontUIResource)
        UIManager.put (key, f);
      }
    } 
}
