package com.ruleengine.rule_enginee;
public class Node {
    private String type;  // "operator" for AND/OR, "operand" for conditions
    private Node left;    // Left child
    private Node right;   // Right child
    private Object value;  // Optional value (e.g., condition or operator)

    // Constructor for operators
    public Node(String type, Node left, Node right, Object value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    // Constructor for operands
    public Node(String type, Object value) {
        this.type = type;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}