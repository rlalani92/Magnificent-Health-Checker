package config;

public class HealthCheckerConfig {
    public static final int RETRY_FREQUENCY = 2; // Number of seconds to retry again
    public static final String URL = "http://localhost:12345/";
    public static final int MONITORING_WINDOW=15 * 60 * 1000;
}
