package com.ruleengine.rule_enginee;



import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final Database database = new Database();

    public RuleController() {
        // Create the database table if it doesn't exist
        database.createTable();
    }

    @PostMapping("/create")
    public Node createRule(@RequestBody String ruleString) {
        Node ast = parseRuleToAST(ruleString);
        // Store the rule in the database (optional)
        // database.saveRule(ruleString); // Implement saveRule method in Database
        return ast;
    }

    @PostMapping("/combine")
    public Node combineRules(@RequestBody String[] rules) {
        Node combinedAst = combineRuleASTs(rules);
        return combinedAst;
    }

    @PostMapping("/evaluate")
    public boolean evaluateRule(@RequestBody EvaluationRequest request) {
        return evaluateAST(request.getAst(), request.getData());
    }

    Node parseRuleToAST(String ruleString) {
        // Simple parsing logic for demonstration (this should be more robust)
        String[] tokens = ruleString.split(" ");
        Node root = new Node("operator", null, null, tokens[1]); // e.g., ">"
        Node left = new Node("operand", null, null, tokens[0]); // e.g., "age"
        Node right = new Node("operand", null, null, Integer.parseInt(tokens[2])); // e.g., "30"

        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    Node combineRuleASTs(String[] rules) {
        Node root = null;
        for (String rule : rules) {
            Node ast = parseRuleToAST(rule);
            if (root == null) {
                root = ast;
            } else {
                root = new Node("operator", root, ast, "AND"); // Combine using AND
            }
        }
        return root;
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
        
        // Basic logic for AND/OR operators
        switch (ast.getValue().toString()) {
            case "AND":
                return leftValue && rightValue;
            case "OR":
                return leftValue || rightValue;
            default:
                return false;
        }
    }

    boolean evaluateCondition(String condition, Map<String, Object> data) {
        String[] parts = condition.split(" ");
        String attribute = parts[0]; // e.g., "age"
        String operator = parts[1]; // e.g., ">"
        int value = Integer.parseInt(parts[2]); // e.g., "30"

        // Perform comparison
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