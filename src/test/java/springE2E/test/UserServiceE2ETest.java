package springE2E.test;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import springE2E.MainClassApp;
import springE2E.client.UserServiceClient;
import springE2E.config.TestConfig;
import springE2E.helpers.UserHelper;
import springE2E.model.User;
import springE2E.validations.UserValidations;

@SpringBootTest(classes = {TestConfig.class, MainClassApp.class})
@ContextConfiguration(classes = {TestConfig.class})
@TestPropertySource("classpath:application-test.properties")
public class UserServiceE2ETest {

    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private UserValidations userValidations;
    @Autowired
    private UserHelper userHelper;

    @Test
    @AllureId("1")
    @Description("Getting a user by id")
    public void getUserByIdTest() {
        userValidations.validateUser(userServiceClient.getUserById(1L),
                userHelper.createExpectedUser(1L, "User 1", "Password1"));
    }

    @Test
    @AllureId("2")
    @Description("Creating a user")
    // Contains bug, users are not saved after creation. Unable to retrieve
    public void createUserTest() {
        User user = userHelper.createExpectedUser(99L, "User 99", "Password99");

        userServiceClient.createUser(user);
        userValidations.validateUser(userServiceClient.getUserById(99L), user);
    }

}