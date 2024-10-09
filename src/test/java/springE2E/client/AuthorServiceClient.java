package springE2E.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthorServiceClient {

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;


    @Autowired
    public AuthorServiceClient(RestTemplate restTemplate, @Value("${author.service.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = orderServiceUrl;
    }
}
