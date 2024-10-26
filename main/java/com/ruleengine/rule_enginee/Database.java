package com.ruleengine.rule_enginee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
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

    // Optional: Method to save rules in the database
    public void saveRule(String ruleString) {
        String sql = "INSERT INTO rules(rule_string) VALUES(?)";
        try (Connection conn = this.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ruleString);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}