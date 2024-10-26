package com.ruleengine.rule_enginee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class RuleEngineTest {

    @Test
    public void testParseRuleToAST() {
        RuleController controller = new RuleController();
        Node ast = controller.parseRuleToAST("age > 30");
        assertEquals("operator", ast.getType());
        assertEquals(">", ast.getValue());
    }

    @Test
    public void testEvaluateCondition() {
        RuleController controller = new RuleController();
        Map<String, Object> data = new HashMap<>();
        data.put("age", 35);
        assertTrue(controller.evaluateCondition("age > 30", data));
    }

    @Test
    public void testCombineRules() {
        RuleController controller = new RuleController();
        Node ast = controller.combineRuleASTs(new String[]{"age > 30", "salary > 50000"});
        assertNotNull(ast);
        assertEquals("operator", ast.getType()); // Check if it's combined
    }
}