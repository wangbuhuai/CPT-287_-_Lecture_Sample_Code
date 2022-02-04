// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-04


package hybrid.lecture_2;

public class Linked_List_Questions {
    /** Tests whether a singly-linked list is circular.
        @param node: a random node in the linked list
        @param <T>: Object type
        @return: {true} if the list is circular; {false} otherwise
    */
    public static <T> boolean isCircular(Node<T> node) {
        Node<T> current = node;
        while (current != null) {
            current = current.next;
            if (current == node) { return true; }
        }
        return false;
    }  // Time complexity: O(n)

    /** Returns the item stored in the middle node of a singly-linked list.
        @param head: head node of the linked list
        @param <T>: Object type
        @return: value stored in the middle node
    */
    public static <T> T middleValue(Node<T> head) {
        int count = 0;
        Node<T> p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        p = head;
        for (int i = 0; i < (count - 1) / 2; i++) { p = p.next; }
        return p.data;
    }  // Time complexity: O(n)

    /** Returns the item stored in the middle node of a singly-linked list.
        @param head: head node of the linked list
        @param <T>: an Object type
        @return: value stored in the middle node
    */
    public static <T> T middleValueInOnePass(Node<T> head) {
        boolean isEven = true;
        Node<T> fast = head;  // {fast} advances in every iteration.
        Node<T> slow = head;  // {slow} advances in every even iteration.
        while (fast.next != null) {
            fast = fast.next;
            isEven = !isEven;
            if (isEven) { slow = slow.next; }
        }
        return slow.data;
    }  // Time complexity: O(n)
}