package springE2E.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookServiceClient {

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;

    @Autowired
    public BookServiceClient(RestTemplate restTemplate, @Value("${book.service.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = orderServiceUrl;
    }

}
