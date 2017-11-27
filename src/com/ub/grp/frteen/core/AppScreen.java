package com.ub.grp.frteen.core;

/**
 * Interface to be implemented by every screen of the application. Any
 * application screen is expected to expose a single method getScreen() which
 * will be the final UI output of that screen.
 *
 * @author varunjai
 *
 * @param <T>
 */
public interface AppScreen<T> {
  /**
   * Returns the final UI output of the screen
   * 
   * @return
   */
  T getScreen();

}
