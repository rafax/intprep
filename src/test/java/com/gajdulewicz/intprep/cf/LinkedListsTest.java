package com.gajdulewicz.intprep.cf;

import static com.gajdulewicz.intprep.cf.LinkedLists.addTwoHugeNumbers;
import static com.gajdulewicz.intprep.cf.LinkedLists.isListPalindrome;
import static com.gajdulewicz.intprep.cf.LinkedLists.mergeTwoLinkedLists;
import static com.gajdulewicz.intprep.cf.LinkedLists.removeKFromList;
import static com.gajdulewicz.intprep.cf.LinkedLists.reverse;

import com.gajdulewicz.intprep.cf.LinkedLists.ListNode;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class LinkedListsTest {

  static ListNode<Integer> list(int... a) {
    ListNode<Integer> head = new ListNode<>(a[0]);
    ListNode<Integer> curr = head;
    for (int i = 1; i < a.length; i++) {
      curr.next = new ListNode<>(a[i]);
      curr = curr.next;
    }
    return head;
  }

  static int[] arr(ListNode<Integer> in) {
    List<Integer> res = new ArrayList<>();
    while (in != null) {
      res.add(in.value);
      in = in.next;
    }
    return res.stream().mapToInt(x -> x).toArray();
  }

  @Test
  public void removeKFromListTest() {
    Truth.assertThat(arr(removeKFromList(list(3, 1, 2, 3, 4, 5), 3)))
        .isEqualTo(new int[]{1, 2, 4, 5});
    Truth.assertThat(arr(removeKFromList(list(1, 2, 3, 3), 3)))
        .isEqualTo(new int[]{1, 2});
  }

  @Test
  public void isListPalindromeTest() {
    Truth.assertThat(isListPalindrome(list(1, 2, 3, 2, 1)))
        .isTrue();
    Truth.assertThat(isListPalindrome(list(1, 2, 3, 2, 2)))
        .isFalse();
  }

  @Test
  public void addTwoHugeNumbersTest() {
    Truth.assertThat(
        arr(addTwoHugeNumbers(list(9876, 5432, 1999), list(1, 8001))))
        .isEqualTo(new int[]{9876, 5434, 0});
  }


  @Test
  public void reverseTest() {
    Truth.assertThat(arr(reverse(list(1, 2, 3, 4)))).isEqualTo(new int[]{4, 3, 2, 1});
  }

  @Test
  public void mergeTwoLinkedListsTest() {
    Truth.assertThat(arr(mergeTwoLinkedLists(list(1, 3, 5), list(0, 2, 7))))
        .isEqualTo(new int[]{0, 1, 2, 3, 5, 7});
  }

}