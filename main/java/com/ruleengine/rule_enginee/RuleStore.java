package com.ruleengine.rule_enginee;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RuleStore {
    private final String url = "jdbc:sqlite:rules.db";

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS rules (id INTEGER PRIMARY KEY, rule_string TEXT NOT NULL)";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveRule(String ruleString) {
        String sql = "INSERT INTO rules(rule_string) VALUES(?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ruleString);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getAllRules() {
        List<String> rules = new ArrayList<>();
        String sql = "SELECT rule_string FROM rules";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rules.add(rs.getString("rule_string"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rules;
    }
    
    public void updateRule(int id, String newRuleString) {
        String sql = "UPDATE rules SET rule_string = ? WHERE id = ?";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newRuleString);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRule(int id) {
        String sql = "DELETE FROM rules WHERE id = ?";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
