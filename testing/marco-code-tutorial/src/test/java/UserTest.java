import org.assertj.core.api.Assertions;
import org.example.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;


public class UserTest {

    User user;
    @BeforeEach
    void setUp(){
        user = new User("John", 20, false, LocalDate.of(1990, 1, 1));
        System.out.println("Setup was called");
    }


    @AfterEach
    void cleanup(){
        user = null ;
        System.out.println("Cleaup was called");
    }


    @Test
    @DisplayName("User should be at least 18")
    public void user_should_be_at_least_18(){
         org.assertj.core.api.Assertions.assertThat(user.age()).isGreaterThan(18);

    }

    @Test
    @DisplayName("User should be beginning with Jo")
    void user_should_be_marco(){
        Assertions.assertThat(user.name()).isEqualTo("John");
        Assertions.assertThat(user.blocked())
                .as("User %s should be blockend", user.name())
                .isTrue();
    }

    @Test
    @DisplayName("User validate json data")
    void user_should_validate_json(){
        assertThatJson(user).isEqualTo("{\"name\":\"John\", \"age\":20, \"blocked\":false, \"birthDate\": [1990,1,1] }");
    }
    @ParameterizedTest
    @ValueSource(ints = {20,50,80})
    void all_friends_should_at_least_be_18(int age){
     Assertions.assertThat(age).isGreaterThan(18);
    }
}
