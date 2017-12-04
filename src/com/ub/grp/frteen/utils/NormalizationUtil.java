package com.ub.grp.frteen.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Utility class for score normalization.
 * 
 * @author varunjai
 *
 */
public class NormalizationUtil {
  /**
   * This method is used to normalize the scores of all the members.
   * 
   * @param memberScores
   *          The map of member name to member score
   * @param totalScore
   *          The total score of all the members
   * @return
   */
  public static Map<String, Double> getNormalizedScores(
      Map<String, Integer> memberScores, int totalScore) {
    final Map<String, Double> member2NormalizedScores = new HashMap<>();

    for (final Entry<String, Integer> memberScore : memberScores.entrySet()) {

      // no score
      if (totalScore == 0) {
        member2NormalizedScores.put(memberScore.getKey(),
            1.0 / memberScores.size());
        continue;
      }

      // compute score
      member2NormalizedScores.put(memberScore.getKey(),
          Double.valueOf(memberScore.getValue()) / totalScore);
    }// for

    return member2NormalizedScores;
  }
}
