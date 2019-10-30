package service;

import config.HealthCheckerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Monitor {

    private static Logger LOGGER = LoggerFactory.getLogger(Monitor.class);

    public void checkHealth () throws InterruptedException {
        HealthChecker healthChecker = new HealthChecker();

        long startTime = new Date().getTime();
        long window = 0;
        int successCount=0;
        int failedCount=0;
        int notRespondingCount=0;

        while (true){
            long currentTime = new Date().getTime();
            long thisWindow = (currentTime - startTime) / HealthCheckerConfig.MONITORING_WINDOW;
            double windowSecond = ((currentTime - startTime) % HealthCheckerConfig.MONITORING_WINDOW ) / 1000.0;

            if (thisWindow != window){
                window = thisWindow;
                successCount = 0;
                failedCount = 0;
                notRespondingCount = 0;
            }

            HealthCheckResponse healthCheckResponse = healthChecker.checkHealth(HealthCheckerConfig.URL);

            if (healthCheckResponse.equals(HealthCheckResponse.SUCCESS)){
                successCount++;
                LOGGER.info(String.format("Server %s is healthy for %.2f %% in last %.2f second" , HealthCheckerConfig.URL , successCount * 100.0/(successCount+failedCount+notRespondingCount), windowSecond));
            }

            if (healthCheckResponse.equals(HealthCheckResponse.FAILURE)){
                failedCount++;
                LOGGER.warn(String.format("Server %s is not healthy for %.2f %% in last %.2f second" , HealthCheckerConfig.URL , failedCount * 100.0/(successCount+failedCount+notRespondingCount), windowSecond));
            }

            if (healthCheckResponse.equals(HealthCheckResponse.NOT_RESPONDING)){
                notRespondingCount++;
                LOGGER.error(String.format("Server %s is not responding for %.2f %% in last %.2f second" , HealthCheckerConfig.URL , notRespondingCount * 100.0/(successCount+failedCount+notRespondingCount), windowSecond));
            }

            Thread.sleep(HealthCheckerConfig.RETRY_FREQUENCY * 1000);
        }
    }
}
