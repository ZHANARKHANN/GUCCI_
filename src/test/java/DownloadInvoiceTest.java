import com.gucci.entities.UserGenerated;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.CartPage;
import com.gucci.layers.web.page.selections.CheckoutPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class DownloadInvoiceTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 24: Download Invoice after purchase order")
    @Owner("Ulugbek")
    @Tag("Test Case 24")
    public void downloadInvoiceAfterPurchaseOrder() {

        var user = UserGenerated.randomUser();
        var productName = "Blue Top";
        var textArea = "Blue Top top";

        open("", HomePage.class)
                .waitForPageLoaded()
                .addProductToCart(productName)
                .clickContinueShopping()
                .clickCartBtn()
                .waitForPageLoaded()
                .clickProceedCheckout(CartPage.class)
                .clickLoginRegister()
                .waitForPageLoaded()
                .fillName(user.getName())
                .fillEmail(user.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signUpNewUser(user)
                .waitForPageLoaded()
                .clickContinueBtn()
                .waitForPageLoaded()
                .verifyLoggedAsUserNameVisible(user.getName())
                .clickCartBtn()
                .waitForPageLoaded()
                .clickProceedCheckout(CheckoutPage.class)
                .waitForPageLoaded()
                .verifyReviewOrderIsVisible()
                .inputDescriptionTextArea(textArea)
                .clickPlaceOrderBtn()
                .waitForPageLoaded()
                .fillPaymentOrder()
                .clickPayAndConfirmOrder()
                .verifyOrderPlacedSuccessfully()
                .waitForPageLoaded()
                .clickDownloadInvoiceBtn()
                .verifyInvoiceIsDownloaded()
                .clickContinueBtn()
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinue();
    }
}
