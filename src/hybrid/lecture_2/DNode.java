// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-04


package hybrid.lecture_2;

/** A doubly-linked list node */
public class DNode<T> {
    public T data;
    public DNode<T> next, prev;
    public DNode(T value) { data = value; }
}