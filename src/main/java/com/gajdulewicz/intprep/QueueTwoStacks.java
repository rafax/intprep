package com.gajdulewicz.intprep;

import java.util.Scanner;
import java.util.Stack;

/** Created by gajduler on 7/21/17. */
public class QueueTwoStacks {
  public static void main(String[] args) {
    MyQueue<Integer> queue = new MyQueue<Integer>();

    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();

    for (int i = 0; i < n; i++) {
      int operation = scan.nextInt();
      if (operation == 1) { // enqueue
        queue.enqueue(scan.nextInt());
      } else if (operation == 2) { // dequeue
        queue.dequeue();
      } else if (operation == 3) { // print/peek
        System.out.println(queue.peek());
      }
    }
    scan.close();
  }

  public static class MyQueue<T> {
    Stack<T> inbox = new Stack<>();
    Stack<T> outbox = new Stack<>();

    public void enqueue(T elem) {
      inbox.push(elem);
    }

    public T dequeue() {
      if (outbox.isEmpty()) {
        while (!inbox.isEmpty()) {
          outbox.push(inbox.pop());
        }
      }
      return outbox.pop();
    }

    public T peek() {
      if (outbox.isEmpty()) {
        while (!inbox.isEmpty()) {
          outbox.push(inbox.pop());
        }
      }
      return outbox.peek();
    }
  }
}
