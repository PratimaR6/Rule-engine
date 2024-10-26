package com.ruleengine.rule_enginee;



import java.util.Map;

public class EvaluationRequest {
    private Node ast;                // The Abstract Syntax Tree
    private Map<String, Object> data; // The data to evaluate against

    // Constructor
    public EvaluationRequest(Node ast, Map<String, Object> data) {
        this.ast = ast;
        this.data = data;
    }

    // Getters and Setters
    public Node getAst() {
        return ast;
    }

    public void setAst(Node ast) {
        this.ast = ast;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}