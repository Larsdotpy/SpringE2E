package springE2E.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springE2E.client.UserServiceClient;
import springE2E.model.User;

@Component
public class UserHelper {

    @Autowired
    UserServiceClient userServiceClient;

    public User createExpectedUser(Long id, String name, String password) {
        return User.builder()
                .id(id)
                .userName(name)
                .password(password)
                .build();
    }

}
