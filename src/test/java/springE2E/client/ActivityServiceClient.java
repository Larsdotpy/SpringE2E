package springE2E.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ActivityServiceClient {

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;

    @Autowired
    public ActivityServiceClient(RestTemplate restTemplate, @Value("${activity.service.url}") String activityServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = activityServiceUrl;
    }
}
