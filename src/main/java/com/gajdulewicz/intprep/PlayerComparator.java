package com.gajdulewicz.intprep;

import java.util.Arrays;
import java.util.Comparator;

public class PlayerComparator implements Comparator<PlayerComparator.Player> {

  @Override
  public int compare(Player o1, Player o2) {
    if (o1.score == o2.score) {
      return o1.name.compareTo(o2.name);
    }
    return -Integer.compare(o1.score, o2.score);
  }

  class Player {
    String name;
    int score;

    Player(String name, int score) {
      this.name = name;
      this.score = score;
    }
  }

  public static void main(String[] args) {
    Arrays.sort(new Player[] {}, new PlayerComparator());
  }
}
