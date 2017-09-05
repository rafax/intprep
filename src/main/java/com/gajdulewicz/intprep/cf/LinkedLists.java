package com.gajdulewicz.intprep.cf;

import java.util.Objects;

/** Created by gajduler on 8/23/17. */
public class LinkedLists {

  static class ListNode<T> {

    ListNode(T x) {
      value = x;
    }

    T value;
    ListNode<T> next;
  }

  static ListNode<Integer> removeKFromList(ListNode<Integer> l, int k) {
    if (l == null) {
      return null;
    }
    final ListNode<Integer> sentinel = new ListNode<>(Integer.MIN_VALUE);
    ListNode<Integer> prev = sentinel, curr = l;
    sentinel.next = l;
    while (curr != null) {
      if (curr.value.equals(k)) {
        prev.next = curr.next;
      } else {
        prev = curr;
      }
      curr = curr.next;
    }
    return sentinel.next;
  }

  static boolean isListPalindrome(ListNode<Integer> l) {
    if (l == null) {
      return true;
    }
    ListNode<Integer> r = reverse(l);
    while (r != null && l != null) {
      if (!Objects.equals(r.value, l.value)) return false;
      r = r.next;
      l = l.next;
    }
    return Objects.equals(r.value, l.value);
  }

  static ListNode<Integer> addTwoHugeNumbers(ListNode<Integer> a, ListNode<Integer> b) {
    ListNode<Integer> ra = reverse(a);
    ListNode<Integer> rb = reverse(b);
    ListNode<Integer> res = null, curr = null;
    ListNode<Integer> zero = new ListNode<>(0);
    int carry = 0;
    while (ra != null || rb != null) {
      AddResult add = add(ra != null ? ra : zero, rb != null ? rb : zero, carry);
      if (res == null) {
        res = new ListNode<>(add.n);
        curr = res;
      } else {
        curr.next = new ListNode<>(add.n);
        curr = curr.next;
      }
      carry = add.carry;
      ra = ra != null ? ra.next : ra;
      rb = rb != null ? rb.next : rb;
    }
    if (carry > 0) {
      curr.next = new ListNode<>(carry);
    }
    return reverse(res);
  }

  static AddResult add(ListNode<Integer> a, ListNode<Integer> b, int carry) {
    int res = a.value + b.value + carry;
    return new AddResult(res % 10000, res / 10000);
  }

  static ListNode<Integer> reverse(ListNode<Integer> b) {
    ListNode<Integer> curr = new ListNode<>(b.value), next = b.next;
    while (next != null) {
      ListNode<Integer> x = new ListNode<>(next.value);
      x.next = curr;
      curr = x;
      next = next.next;
    }
    return curr;
  }

  static ListNode<Integer> mergeTwoLinkedLists(ListNode<Integer> a, ListNode<Integer> b) {
    ListNode<Integer> res = null, curr = null;
    while (a != null || b != null) {
      ListNode<Integer> n;
      if (a != null) {
        if (b != null) {
          if (a.value < b.value) {
            n = new ListNode<>(a.value);
            a = a.next;
          } else {
            n = new ListNode<>(b.value);
            b = b.next;
          }
        } else {
          n = new ListNode<>(a.value);
          a = a.next;
        }

      } else {
        n = new ListNode<>(b.value);
        b = b.next;
      }
      if (res == null) {
        res = n;
        curr = n;
      } else {
        curr.next = n;
        curr = curr.next;
      }
    }
    return res;
  }

  public static class AddResult {

    final int n;
    final int carry;

    public AddResult(int n, int carry) {
      this.n = n;
      this.carry = carry;
    }
  }
}
