package com.ub.grp.frteen.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * Utility class for adding font and styling changes.
 *
 */
public class FontUtils {
  /**
   * Get the default font for the application.
   * 
   * @return
   */
  public static Font getDefaultFont() {
    return new Font(null, Font.PLAIN, 15);
  }

  /**
   * Get the UI font by configuration.
   * 
   * @param font
   *          !null
   */
  public static void setUIFont(javax.swing.plaf.FontUIResource font) {
    java.util.Enumeration<Object> keys = UIManager.getLookAndFeelDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof javax.swing.plaf.FontUIResource)
        UIManager.put(key, font);
    }
  }

  /**
   * Get the headline text as a {@link JLabel}
   * 
   * @param headlineTxt
   *          !blank
   * @return
   */
  public static JLabel getHeadline(String headlineTxt) {
    final JLabel headline = new JLabel(headlineTxt);
    headline.setFont(new Font(AppConstants.DEFAULT_FONT, Font.BOLD, 30));
    headline.setAlignmentX(Component.CENTER_ALIGNMENT);
    headline.setForeground(Color.BLACK);
    return headline;
  }
}
