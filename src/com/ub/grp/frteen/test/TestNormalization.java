package com.ub.grp.frteen.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ub.grp.frteen.utils.NormalizationUtil;

public class TestNormalization {

  private HashMap<String, Integer> scores;
  /**
   * Test setup
   */
  @Before
  public void setUp() {
    scores = new HashMap<String, Integer>();
  }

  /**
   * This test will test a scenario where some non-zeros scores have been added
   * for each member. Expected result is that all members should get the same
   * score.
   */
  @Test
  public void testNormalCase() {
    scores.put("Nick", 10);
    scores.put("Shivam", 10);
    scores.put("Hiro", 10);
    scores.put("Varun", 10);
    scores.put("Ralph", 10);
    Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);

    assertEquals(result.get("Nick"), (Double) 0.2);
    assertEquals(result.get("Shivam"), (Double) 0.2);
    assertEquals(result.get("Hiro"), (Double) 0.2);
    assertEquals(result.get("Varun"), (Double) 0.2);
    assertEquals(result.get("Ralph"), (Double) 0.2);
    
    // check total normalized score is 1
    Double total = 0.0;
    for(Double score : result.values()) {
      total += score;
    }
    
    assertEquals((Double)1.0, total);
  }

  /**
   * This test validates that every member has been assigned some non-zero score
   * and all members have not been assigned equal scores. Expected result is
   * that all scores should get normalized for each member based on the score
   * assigned to member against the cumulative score.
   */
  @Test
  public void testNormalCase2() {
    scores.put("Nick", 1);
    scores.put("Shivam", 2);
    scores.put("Hiro", 3);
    scores.put("Varun", 3);
    scores.put("Ralph", 1);
    Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);

    assertEquals(result.get("Nick"), (Double) 0.1);
    assertEquals(result.get("Shivam"), (Double) 0.2);
    assertEquals(result.get("Hiro"), (Double) 0.3);
    assertEquals(result.get("Varun"), (Double) 0.3);
    assertEquals(result.get("Ralph"), (Double) 0.1);
    
    // check total normalized score is 1
    Double total = 0.0;
    for(Double score : result.values()) {
      total += score;
    }
    
    assertEquals((Double)1.0, total);
  }
  
  /**
   * This test tests the for loop in such a way that it makes only one pass
   */
  @Test
  public void testNormalCase3() {
	  scores.put("Shivam", 3);
	  Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);
	  assertEquals(result.get("Shivam"),(Double)1.0);
	// check total normalized score is 1
	    Double total = 0.0;
	    for(Double score : result.values()) {
	      total += score;
	    }
	    
	    assertEquals((Double)1.0, total);
  }
  
  /**
   * This test tests the for loop in such a way that it makes only two passes
   */
  @Test
  public void testNormalCase4() {
	  scores.put("Shivam", 3);
	  scores.put("Varun", 3);
	  Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);
	  assertEquals(result.get("Shivam"),(Double)0.5);
	  assertEquals(result.get("Varun"),(Double)0.5);
	// check total normalized score is 1
	    Double total = 0.0;
	    for(Double score : result.values()) {
	      total += score;
	    }
	    
	    assertEquals((Double)1.0, total);
  }
  /**
   * This test validates that all members have been assigned zero scores.
   * Expected result is that all members should get the same score.
   */
  @Test
  public void testAllZeros() {
    scores.put("Nick", 0);
    scores.put("Shivam", 0);
    scores.put("Hiro", 0);
    scores.put("Varun", 0);
    scores.put("Ralph", 0);
    Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);

    assertEquals(result.get("Nick"), (Double) 0.2);
    assertEquals(result.get("Shivam"), (Double) 0.2);
    assertEquals(result.get("Hiro"), (Double) 0.2);
    assertEquals(result.get("Varun"), (Double) 0.2);
    assertEquals(result.get("Ralph"), (Double) 0.2);

    // check total normalized score is 1
    Double total = 0.0;
    for(Double score : result.values()) {
      total += score;
    }
    
    assertEquals((Double)1.0, total);
  }
  /**
   * This test case expects that no scores have been passed. Expected result is
   * 0. This tests the simple for loop in a way that loop is skipped entirely and this case is
   * handled beforehand in the TestNormalization class
   */
  @Test
  public void testEmptyScores() {
    Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);
    assertEquals(result.size(), 0);
  }
  /**
   * This test case validates an invalid input like passing a negative score
   * value. Expected result is an illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeScores() {
    scores.put("Nick", -10);
    scores.put("Shivam", 7);
    scores.put("Hiro", 8);
    NormalizationUtil.getNormalizedScores(scores);
  }

  /**
   * This test case validates an invalid input like passing a null object.
   * Expected result is an illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidData() {
    NormalizationUtil.getNormalizedScores(null);
  }
}
