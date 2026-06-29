package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        private final String url;
        private final String user;
        private final String password;

        public DatabaseConnection() {
            this.url = AppConfig.getDbUrl();
            this.user = AppConfig.getDbUser();
            this.password = AppConfig.getDbPassword();
        }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }
}
