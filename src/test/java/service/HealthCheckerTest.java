package service;


import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

public class HealthCheckerTest {

    HealthChecker healthChecker = new HealthChecker();

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 9000);

    private MockServerClient mockServerClient;

    @Test
    public void testHealthCheckSuccess() {
        mockServerClient.when(HttpRequest.request("/")).respond(HttpResponse.response().withStatusCode(200));
        assert HealthCheckResponse.SUCCESS == healthChecker.checkHealth("http://localhost:9000");
    }

    @Test
    public void testHealthCheckFailure() {
        mockServerClient.when(HttpRequest.request("/")).respond(HttpResponse.response().withStatusCode(500));
        assert HealthCheckResponse.FAILURE == healthChecker.checkHealth("http://localhost:9000");
    }

    @Test
    public void testHealthCheckNotResponding()  {
        assert HealthCheckResponse.NOT_RESPONDING == healthChecker.checkHealth("http://localhost:9001");
    }
}
