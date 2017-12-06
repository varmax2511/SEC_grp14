package com.ub.grp.frteen.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ub.grp.frteen.utils.NormalizationUtil;

public class TestNormalization {
	
	private HashMap<String, Integer> scores;
	
	@Before
	public void setUp() {
		scores = new HashMap<String, Integer>();
	}
	
	@Test
	public void testNormalCase() {
		scores.put("Nick", 10);
		scores.put("Shivam", 10);
		scores.put("Hiro", 10);
		scores.put("Varun", 10);
		scores.put("Ralph", 10);
		Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);

		assertEquals(result.get("Nick"), (Double)0.2);
		assertEquals(result.get("Shivam"), (Double)0.2);
		assertEquals(result.get("Hiro"), (Double)0.2);
		assertEquals(result.get("Varun"), (Double)0.2);
		assertEquals(result.get("Ralph"), (Double)0.2);
	}
	
	@Test
	public void testNormalCase2() {
		scores.put("Nick", 1);
		scores.put("Shivam", 2);
		scores.put("Hiro", 3);
		scores.put("Varun", 3);
		scores.put("Ralph", 1);
		Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);

		assertEquals(result.get("Nick"), (Double)0.1);
		assertEquals(result.get("Shivam"), (Double)0.2);
		assertEquals(result.get("Hiro"), (Double)0.3);
		assertEquals(result.get("Varun"), (Double)0.3);
		assertEquals(result.get("Ralph"), (Double)0.1);
	}
	
	@Test
	public void testAllZeros() {
		scores.put("Nick", 0);
		scores.put("Shivam", 0);
		scores.put("Hiro", 0);
		scores.put("Varun", 0);
		scores.put("Ralph", 0);
		Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);
		
		assertEquals(result.get("Nick"), (Double)0.2);
		assertEquals(result.get("Shivam"), (Double)0.2);
		assertEquals(result.get("Hiro"), (Double)0.2);
		assertEquals(result.get("Varun"), (Double)0.2);
		assertEquals(result.get("Ralph"), (Double)0.2);
	}
	
	@Test
	public void testEmptyScores() {
		Map<String, Double> result = NormalizationUtil.getNormalizedScores(scores);
		assertEquals(result.size(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testNegativeScores() {
		scores.put("Nick", -10);
		scores.put("Shivam", 7);
		scores.put("Hiro", 8);
		NormalizationUtil.getNormalizedScores(scores);
	}

}
