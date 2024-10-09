package springE2E.client;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;
import springE2E.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserServiceClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    @Autowired
    public UserServiceClient(RestTemplate restTemplate, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }
    // 1. Get user by ID (basic GET request)
    public User getUserById(Long id) {
        return restTemplate.getForObject(userServiceUrl + id, User.class);
    }

    // 2. Create a new user (basic POST request)
    public User createUser(User user) {
        return restTemplate.postForObject(userServiceUrl, user, User.class);
    }

    // 3. Set custom request headers (e.g., Content-Type: application/json)
    public ResponseEntity<String> createUserWithHeaders(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Custom-Header", "CustomValue");  // Example of setting a custom header

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        // Send the request with custom headers
        return restTemplate.exchange(userServiceUrl + "/users", HttpMethod.POST, requestEntity, String.class);
    }

    // 4. Handling timeouts and errors
    public User getUserWithTimeout(Long id) {
        try {
            return restTemplate.getForObject(userServiceUrl + "/users/" + id, User.class);
        } catch (HttpClientErrorException e) {
            System.out.println("HTTP error: " + e.getStatusCode());
            return null;
        } catch (ResourceAccessException e) {
            System.out.println("Timeout or connection error: " + e.getMessage());
            return null;
        }
    }

    // 5. Reading the response as a String
    public String getUserRawResponse(Long id) {
        ResponseEntity<String> response = restTemplate.getForEntity(userServiceUrl + "/users/" + id, String.class);
        return response.getBody();  // Return the raw response body as a String
    }

//    // 6. Example: Manually parsing JSON response (using Jackson)
//    public User getUserByIdManualParsing(Long id) {
//        String jsonResponse = restTemplate.getForObject(userServiceUrl + "/users/" + id, String.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            return objectMapper.readValue(jsonResponse, User.class);  // Manually convert JSON string to User object
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    // 7. Using parameters in URL
    public ResponseEntity<User> getUserWithQueryParams(Long id, String status) {
        // Build the URI with query parameters
        String url = UriComponentsBuilder.fromHttpUrl(userServiceUrl + "/users")
                .queryParam("id", id)
                .queryParam("status", status)
                .toUriString();

        // Send GET request and get the response entity
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        // Check the status code
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            // Return the User object if the request was successful
            return response;
        } else {
            // Handle error or return null
            System.out.println("Error: " + statusCode);
            return null;
        }
    }
}

