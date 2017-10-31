package com.gajdulewicz.intprep;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RingRoads {

  static double[] ringRoads(int innerRing, int outerRing, double[] roads, double[][] travels) {
    List<Double> res = new ArrayList<>();
    for (double[] travel : travels) {
      double from = travel[0], to = travel[1];
      double shortest = Double.MAX_VALUE;
      for (double r : roads) {
        double curr =
            shortestArcLength(innerRing, from, r)
                + outerRing
                - innerRing
                + shortestArcLength(outerRing, r, to);
        if (curr < shortest) {
          shortest = curr;
        }
      }
      res.add(shortest);
    }
    return res.stream()
        .mapToDouble(c -> new BigDecimal(c).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue())
        .toArray();
  }

  static double shortestArcLength(int radius, double from, double to) {
    double ccdegrees = Math.abs(to - from);
    double cdegrees = 360d - ccdegrees;
    return (Math.min(ccdegrees, cdegrees) / 360d) * 2 * Math.PI * radius;
  }
}
