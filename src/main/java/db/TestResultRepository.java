package db;

import data.TestConfig;

import java.sql.*;

public class TestResultRepository {

    public static void init() {
        try (Connection conn = DriverManager.getConnection(TestConfig.DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS test_results (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    test_name TEXT UNIQUE,
                    status TEXT,
                    execution_time DATETIME
                )
            """);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(String testName, String status) {
        try (Connection conn = DriverManager.getConnection(TestConfig.DB_URL);
             PreparedStatement stmt = conn.prepareStatement("""
                INSERT INTO test_results(test_name, status, execution_time)
                VALUES (?, ?, datetime('now'))
                ON CONFLICT(test_name)
                DO UPDATE SET status = excluded.status,
                              execution_time = datetime('now')
            """)) {

            stmt.setString(1, testName);
            stmt.setString(2, status);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}