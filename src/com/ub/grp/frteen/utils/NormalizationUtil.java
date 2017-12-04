package com.ub.grp.frteen.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NormalizationUtil {

  public static Map<String, Double> getNormalizedScores(
      Map<String, Integer> memberScores, int totalScore) {
    Map<String, Double> member2NormalizedScores = new HashMap<>();

    for (Entry<String, Integer> memberScore : memberScores.entrySet()) {

      // no score
      if (totalScore == 0) {
        member2NormalizedScores.put(memberScore.getKey(), 0.0);
        continue;
      }

      member2NormalizedScores.put(memberScore.getKey(),
          Double.valueOf(memberScore.getValue()) /  totalScore);
    }

    return member2NormalizedScores;
  }
  
  public static void main(String[] args){
    Map<String, Integer> h = new HashMap<>();
    h.put("v", 10);
    h.put("c", 10);
    getNormalizedScores(h, 20);
  }
}
