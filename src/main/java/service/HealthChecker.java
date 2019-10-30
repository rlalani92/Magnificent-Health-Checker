package service;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HealthChecker {
    private static final OkHttpClient client = new OkHttpClient.Builder().build() ;

    HealthCheckResponse checkHealth(String url)  {

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                return HealthCheckResponse.SUCCESS;
            }
        } catch (IOException e) {
            return HealthCheckResponse.NOT_RESPONDING;
        }

        return HealthCheckResponse.FAILURE;

    }
}
