package springE2E.client;

import springE2E.model.CoverPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CoverPhotoServiceClient {

    private final RestTemplate restTemplate;
    private final String paymentServiceUrl;

    @Autowired
    public CoverPhotoServiceClient(RestTemplate restTemplate, @Value("${coverPhoto.service.url}") String paymentServiceUrl) {
        this.restTemplate = restTemplate;
        this.paymentServiceUrl = paymentServiceUrl;
    }

    public CoverPhoto processPayment(CoverPhoto coverPhoto) {
        return restTemplate.postForObject(paymentServiceUrl + "/payments", coverPhoto, CoverPhoto.class);
    }

    public CoverPhoto getPaymentStatus(Long id) {
        return restTemplate.getForObject(paymentServiceUrl + "/payments/" + id, CoverPhoto.class);
    }
}
