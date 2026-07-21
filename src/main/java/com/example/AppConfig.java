package com.example;

public class AppConfig {
    // Константы для режимов
    public static final String MODE_IN_MEMORY = "inmemory";
    public static final String MODE_DB = "db";

    // Имена переменных окружения
    private static final String ENV_MODE = "APP_MODE";
    private static final String ENV_DB_URL = "APP_DB_URL";
    private static final String ENV_DB_USER = "APP_DB_USER";
    private static final String ENV_DB_PASSWORD = "APP_DB_PASSWORD";

    // Значения по умолчанию
    private static final String DEFAULT_MODE = MODE_IN_MEMORY;
    private static final String DEFAULT_DB_URL = "jdbc:postgresql://localhost:5432/project_db";
    private static final String DEFAULT_DB_USER = "user";
    private static final String DEFAULT_DB_PASSWORD = "pass";

    private static String mode;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        loadFromEnvironment();
    }

    private static void loadFromEnvironment() {
        // 1. Режим работы
        mode = System.getenv(ENV_MODE);
        if (mode == null || mode.isEmpty()) {
            mode = DEFAULT_MODE;
        }
        System.out.println("🔧 Режим: " + mode);

        // 2. Настройки БД (если режим DB)
        dbUrl = System.getenv(ENV_DB_URL);
        if (dbUrl == null || dbUrl.isEmpty()) {
            dbUrl = DEFAULT_DB_URL;
        }

        dbUser = System.getenv(ENV_DB_USER);
        if (dbUser == null || dbUser.isEmpty()) {
            dbUser = DEFAULT_DB_USER;
        }

        dbPassword = System.getenv(ENV_DB_PASSWORD);
        if (dbPassword == null || dbPassword.isEmpty()) {
            dbPassword = DEFAULT_DB_PASSWORD;
        }
    }

    // Геттеры

    public static boolean isDbMode() {
        return MODE_DB.equalsIgnoreCase(mode);
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    // Показать текущие настройки
    public static void printConfig() {
        System.out.println("=== Конфигурация ===");
        System.out.println("Режим: " + mode);
        if (isDbMode()) {
            System.out.println("DB URL: " + dbUrl);
            System.out.println("DB User: " + dbUser);
            System.out.println("DB Password: " + "*".repeat(dbPassword.length()));
        }
        System.out.println("====================");
    }
}
