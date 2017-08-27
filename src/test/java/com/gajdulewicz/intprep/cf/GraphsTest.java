package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Graphs.*;
import static com.gajdulewicz.intprep.cf.HeapsStacksQueues.hasDeadlock;

public class GraphsTest {

  @Test
  public void hasDeadlockTest() {
    Truth.assertThat(hasDeadlock(new int[][] {{1}, {2}, {3, 4}, {4}, {0}})).isTrue();
    Truth.assertThat(hasDeadlock(new int[][] {{1, 2, 3}, {2, 3}, {3}, {}})).isFalse();
  }

  @Test
  public void singlePointOfFailureTest() {
    Truth.assertThat(
            singlePointOfFailure(
                new int[][] {
                  {0, 1, 1, 0, 0, 0, 0},
                  {1, 0, 1, 0, 0, 0, 0},
                  {1, 1, 0, 0, 0, 0, 1},
                  {0, 0, 0, 0, 1, 1, 1},
                  {0, 0, 0, 1, 0, 1, 0},
                  {0, 0, 0, 1, 1, 0, 0},
                  {0, 0, 1, 1, 0, 0, 0}
                }))
        .isEqualTo(2);
    Truth.assertThat(
            singlePointOfFailure(
                new int[][] {
                  {0, 1, 1, 1, 0, 0},
                  {1, 0, 1, 0, 0, 0},
                  {1, 1, 0, 0, 0, 0},
                  {1, 0, 0, 0, 1, 1},
                  {0, 0, 0, 1, 0, 0},
                  {0, 0, 0, 1, 0, 0}
                }))
        .isEqualTo(3);
    Truth.assertThat(
            singlePointOfFailure(
                new int[][] {
                  {0, 1, 1, 1, 1},
                  {1, 0, 0, 0, 0},
                  {1, 0, 0, 0, 0},
                  {1, 0, 0, 0, 0},
                  {1, 0, 0, 0, 0}
                }))
        .isEqualTo(4);
    Truth.assertThat(singlePointOfFailure(new int[][] {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}))
        .isEqualTo(0);
  }

  @Test
  public void feedingTimeTest() {
    Truth.assertThat(
            feedingTime(
                new String[][] {
                  {"Tiger", "Lima", "Frog"},
                  {"Tiger", "Zebra", "Lion"},
                  {"Tiger", "Rabbit"},
                  {"Emu", "Zebra", "Rabbit"}
                }))
        .isEqualTo(3);
    Truth.assertThat(
            feedingTime(
                new String[][] {
                  {"Tiger", "Lima", "Frog"},
                  {"Tiger", "Zebra", "Lion"},
                  {"Tiger", "Rabbit"},
                  {"Lima", "Zebra", "Rabbit"}
                }))
        .isEqualTo(4);
    Truth.assertThat(
            feedingTime(
                new String[][] {
                  {"Lion", "Emu"},
                  {"Giraffe", "Peacock"},
                  {"Lima"},
                  {"Tiger", "Cheetah", "Slugs"},
                  {"Snakes", "Sealion"}
                }))
        .isEqualTo(1);
    Truth.assertThat(
            feedingTime(
                new String[][] {
                  {"Coati", "Ram"},
                  {"Chameleon", "Buffalo", "Coati"},
                  {"Elk", "Coyote", "Jerboa"},
                  {"Coyote", "Elk"},
                  {"Gorilla", "Chameleon"},
                  {"Fawn", "Alpaca", "Coyote"},
                  {"Raccoon", "Bear", "Coyote", "Walrus"},
                  {"ocelot", "Coyote", "Giraffe"}
                }))
        .isEqualTo(5);
    Truth.assertThat(
            feedingTime(
                new String[][] {
                  {"aa", "ab", "ac", "ad", "ae", "af", "ag", "ah"},
                  {"aa", "ai", "aj", "ba", "bb", "bc", "bd", "be"},
                  {"bf", "bg", "bh", "bi", "bj", "ca", "cb", "cc"},
                  {"ab", "bf", "cd", "ce", "cf", "cg", "ch", "ci", "cj"},
                  {"ac", "ai", "bg", "da", "db", "dc", "dd", "de", "df", "dg"},
                  {"aj", "cd", "da", "dh", "di", "dj", "ea", "eb", "ec", "ed"},
                  {"ad", "bh", "ee", "ef", "eg", "eh", "ei", "ej", "fa"},
                  {"ba", "bi", "ce", "db", "dh", "ee", "fb", "fc", "fd"},
                  {"ae", "bj", "cf", "ef", "fb", "fe", "ff", "fg", "fh", "fi"},
                  {"bb", "ca", "cg", "dc", "di", "fe", "fj", "ga", "gb"},
                  {"af", "dd", "dj", "eg", "fc", "ff", "gc", "gd", "ge"},
                  {"bc", "cb", "ch", "de", "ea", "eh", "fd", "fg"},
                  {"bd", "cc", "ci", "eb", "ei", "fh", "fj", "gc"},
                  {"ag", "be", "cj", "df", "ec", "ej", "fi", "ga", "gd"},
                  {"ah", "dg", "ed", "fa", "gb", "ge"}
                }))
        .isEqualTo(5);
  }

  @Test
  public void flightPlanTest() {
    Truth.assertThat(
            flightPlan(
                new String[][] {
                  {"Chicago", "Denver", "03:00", "06:00"},
                  {"Chicago", "Denver", "03:30", "07:00"},
                  {"Chicago", "Los Angeles", "01:00", "05:00"},
                  {"Denver", "Austin", "06:30", "08:30"},
                  {"Denver", "Austin", "07:30", "09:30"},
                  {"Austin", "Denver", "06:30", "08:30"},
                  {"Los Angeles", "Phoenix", "06:00", "07:00"},
                  {"Los Angeles", "Phoenix", "05:30", "06:50"},
                  {"Phoenix", "Austin", "08:00", "08:40"}
                },
                "Chicago",
                "Austin"))
        .isEqualTo("08:40");

    Truth.assertThat(
            flightPlan(
                new String[][] {
                  {"Qruilrora", "Wrido", "08:15", "09:10"},
                  {"Qruilrora", "Wrido", "09:00", "10:55"},
                  {"Wrido", "Bivront", "11:25", "13:05"},
                  {"Source", "Vlery", "10:00", "11:20"},
                  {"Vlery", "Bivront", "10:15", "11:30"},
                  {"Vlery", "Bivront", "12:30", "13:59"},
                  {"Bivront", "Gladena", "14:30", "20:01"},
                  {"Bivront", "Gladena", "16:05", "20:05"}
                },
                "Qruilrora",
                "Gladena"))
        .isEqualTo("20:01");
  }
}
