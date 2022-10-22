package local.rentmycar.api.service;

import local.rentmycar.api.ApiApplication;
import local.rentmycar.api.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService sut;

    @Test
    void create_User_Expect_User() {
        // arrange
        User user = new User(0, "owner", "testfirstname", "testlastname", "test@test.com", "testaddress1", "NL", "", "12356", null);

        // act
        User result = sut.create(user);

        // assert
        Assert.isInstanceOf(User.class, result);
    }

    @Test
    void create_User_Expect_IllegalArgumentException() {
        // arrange
        User user = new User(0, "owne", "testfirstname", "testlastname", "test@test.com", "testaddress1", "NL", "", "12356", null);

        // act
        try {
            User result = sut.create(user);
        } catch (Exception e) {
            // assert
            Assert.isInstanceOf(IllegalArgumentException.class, e);
        }
    }

    @Test
    void update_User_Expect_Changed_User() {
        // arrange
        User user = sut.create(new User(0, "owner", "testfirstname", "testlastname", "test@test.com", "testaddress1", "NL", "", "12356", null));

        // act
        user.setFirstName("newName");
        user = sut.update(user.getId(), user);

        // assert
        Assert.hasText("newName",user.getFirstName());

    }

    @Test
    void update_User_Expect_IllegalArgumentException() {
        // arrange
        User user = sut.create(new User(0, "owner", "testfirstname", "testlastname", "test@test.com", "testaddress1", "NL", "", "12356", null));

        // act
        try {
            user.setRole("non-existing role");
            User result = sut.update(user.getId(),user);
        } catch (Exception e) {
            // assert
            Assert.isInstanceOf(IllegalArgumentException.class, e);
        }
    }

}
