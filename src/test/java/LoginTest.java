import com.gucci.entities.User;
import com.gucci.entities.UserGenerated;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.gucci.data.Sections.SIGN_IN_LOGIN;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class LoginTest extends BaseWebTest{

    @Test
    @DisplayName("Test Case 2: Login User with correct email and password")
    @Owner("Aliia")
    @Tag("Test Case 2")
    public void loginTestCorrectParams() {

        var firstUser = UserGenerated.randomUser();;

         open("", HomePage.class)
                 .waitForPageLoaded()
                 .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                 .waitForPageLoaded()
                 .fillName(firstUser.getName())
                 .fillEmail(firstUser.getEmail())
                 .clickSignUpBtn(SignUpPage.class)
                 .waitForPageLoaded()
                 .signUpNewUser(firstUser)
                 .waitForPageLoaded()
                 .clickContinueBtn()
                 .verifyLoggedAsUserNameVisible(firstUser.getName())
                 .clickLogout()
                 .clickHome()
                 .waitForPageLoaded()
                 .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                 .loginToYourAccountVisible()
                 .loginUser(firstUser.getEmail(), firstUser.getPassword(), HomePage.class)
                 .verifyLoggedAsUserNameVisible(firstUser.getName())
                 .clickDeleteAccount()
                 .waitForPageLoaded()
                 .clickContinue();
    }

    @Test
    @DisplayName("Test Case 3: Login User with incorrect email and password")
    @Owner("Aliia")
    @Tag("Test Case 3")
    public void loginWithIncorrectParamsTest() {

        var email = "aliia1@gmail.com";
        var password = "123";

      open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .loginToYourAccountVisible()
                .loginUser(email, password, LoginPage.class)
                .paramsIncorrectErrorMessage();
    }

    @Test
    @DisplayName("Test Case 4: Logout User")
    @Owner("Aliia")
    @Tag("Test Case 4")
    public void logout() {

        var firstUser = UserGenerated.randomUser();

        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillName(firstUser.getName())
                .fillEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signUpNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueBtn()
                .verifyLoggedAsUserNameVisible(firstUser.getName())
                .clickLogout()
                .waitForPageLoaded();
    }
}
