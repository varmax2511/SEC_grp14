package com.ub.grp.frteen.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NormalizationUtil {

  public static Map<String, Double> getNormalizedScores(
      HashMap<String, Integer> memberScores, int totalScore) {
    Map<String, Double> member2NormalizedScores = new HashMap<>();

    for (Entry<String, Integer> memberScore : memberScores.entrySet()) {

      // no score
      if (totalScore == 0) {
        member2NormalizedScores.put(memberScore.getKey(), 0.0);
        continue;
      }
      
      member2NormalizedScores.put(memberScore.getKey(),
          (double) (memberScore.getValue() / totalScore));
    }

    return member2NormalizedScores;
  }
}
