package com.gajdulewicz.intprep;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class QueueTwoStacks {
    public static void main(String[] args) {
        process(System.in).stream().map(Object::toString).forEach(System.out::println);
    }

    static List<Integer> process(InputStream in) {
        MyQueue<Integer> queue = new MyQueue<>();
        LinkedList<Integer> output = new LinkedList<>();
        Scanner scan = new Scanner(in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                output.add(queue.peek());
            }
        }
        scan.close();
        return output;
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
