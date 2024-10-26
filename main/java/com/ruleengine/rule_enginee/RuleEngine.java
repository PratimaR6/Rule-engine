package com.ruleengine.rule_enginee;

import java.util.HashMap;
import java.util.Map;

public class RuleEngine {

    public Node parseRuleToAST(String ruleString) {
        // Simple parsing logic for demonstration
        String[] tokens = ruleString.split(" ");
        Node root = new Node("operator", null, null, tokens[1]); // e.g., ">"
        Node left = new Node("operand", null, null, tokens[0]); // e.g., "age"
        Node right = new Node("operand", null, null, Integer.parseInt(tokens[2])); // e.g., "30"

        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    public boolean evaluateRule(Node ast, Map<String, Object> data) {
        return evaluateAST(ast, data);
    }

    private boolean evaluateAST(Node ast, Map<String, Object> data) {
        if (ast.getType().equals("operand")) {
            return evaluateCondition(ast.getValue().toString(), data);
        } else if (ast.getType().equals("operator")) {
            return evaluateOperator(ast, data);
        }
        return false;
    }

    private boolean evaluateOperator(Node ast, Map<String, Object> data) {
        boolean leftValue = evaluateAST(ast.getLeft(), data);
        boolean rightValue = evaluateAST(ast.getRight(), data);
        
        switch (ast.getValue().toString()) {
            case "AND":
                return leftValue && rightValue;
            case "OR":
                return leftValue || rightValue;
            default:
                return false;
        }
    }

    private boolean evaluateCondition(String condition, Map<String, Object> data) {
        String[] parts = condition.split(" ");
        String attribute = parts[0]; // e.g., "age"
        String operator = parts[1]; // e.g., ">"
        int value = Integer.parseInt(parts[2]); // e.g., "30"

        if (data.containsKey(attribute)) {
            int attributeValue = (Integer) data.get(attribute);
            switch (operator) {
                case ">":
                    return attributeValue > value;
                case "<":
                    return attributeValue < value;
                case "=":
                case "==":
                    return attributeValue == value;
                case ">=":
                    return attributeValue >= value;
                case "<=":
                    return attributeValue <= value;
                default:
                    return false;
            }
        }
        return false;
    }
}
