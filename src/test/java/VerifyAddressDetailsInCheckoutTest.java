import com.gucci.entities.UserGenerated;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.CheckoutPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.gucci.data.Sections.SIGN_IN_LOGIN;

@Tag(Tags.SMOKE)

public class VerifyAddressDetailsInCheckoutTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 23: Verify address details in checkout page")
    @Owner("Ulugbek")
    @Tag("Test_Case_23")
    public void addToCartFromRecommendedItems() {

        var firstUser = UserGenerated.randomUser();
        var productName = "Blue Top";

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
                .addProductToCart(productName)
                .clickContinueShopping()
                .clickCartBtn()
                .waitForPageLoaded()
                .clickProceedCheckout(CheckoutPage.class)
                .waitForPageLoaded()
                .verifyDeliveryAndBillingAddressIsSameRegistration(firstUser)
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinue();
    }
}