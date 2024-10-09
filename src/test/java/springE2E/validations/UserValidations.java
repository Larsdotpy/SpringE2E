package springE2E.validations;

import org.springframework.stereotype.Component;
import springE2E.model.User;

@Component
public class UserValidations extends BaseValidations {

    public void validateUser(User actual, User expected) {
        softly.assertThat(actual.getId())
                .as("Verifying user id is equal to " + expected.getId()).isEqualTo(expected.getId());
        softly.assertThat(actual.getUserName())
                .as("Verifying user name is equal to " + expected.getUserName()).isEqualTo(expected.getUserName());
        softly.assertThat(actual.getPassword())
                .as("Verifying user password is equal to " + expected.getPassword()).isEqualTo(expected.getPassword());

        softly.assertAll();
    }
}
