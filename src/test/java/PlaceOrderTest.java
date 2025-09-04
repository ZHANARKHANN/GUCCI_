import com.gucci.entities.User;
import com.gucci.entities.UserGenerated;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.CartPage;
import com.gucci.layers.web.page.selections.CheckoutPage;
import com.gucci.layers.web.page.selections.ProductsPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static com.gucci.data.Sections.*;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class PlaceOrderTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 14: Place Order: Register while Checkout")
    @Owner("Aidai")
    @Tag("Test_case_14")
    public void registerWhileCheckout() {

        User firstUser = UserGenerated.randomUser();
        var productName = "Blue Top";
        var textArea = "Blue Top cringe";

        open("", HomePage.class)
                .waitForPageLoaded()
                .addProductToCart(productName)
                .clickViewCart()
                .waitForPageLoaded()
                .clickProceedCheckout(CartPage.class)
                .clickLoginRegister()
                .fillName(firstUser.getName())
                .fillEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signUpNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueBtn()
                .verifyLoggedAsUserNameVisible(firstUser.getName())
                .clickCartBtn()
                .clickProceedCheckout(CheckoutPage.class)
                .verifyAddressDetailsText()
                .verifyReviewYourOrderText()
                .inputDescriptionTextArea(textArea)
                .clickPlaceOrderBtn()
                .fillPaymentOrder()
                .clickPayAndConfirmOrder()
                .verifyOrderPlacedSuccessfully()
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinue();
    }

    @Test
    @DisplayName("Test Case 15: Place Order: Register before Checkout")
    @Owner("Aidai")
    @Tag("Test_Сase_15")
    public void PlaceOrderRegisterBeforeCheckout() {

        User firstUser = UserGenerated.randomUser();
        var productName = "Blue Top";
        var textArea = "Blue Top cringe";
        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillName(firstUser.getName())
                .fillEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .signUpNewUser(firstUser)
                .clickContinueBtn()
                .waitForPageLoaded()
                .verifyLoggedAsUserNameVisible(firstUser.getName())
                .addProductToCart(productName)
                .clickContinueShopping()
                .clickCartBtn()
                .waitForPageLoaded()
                .clickProceedCheckout(CheckoutPage.class)
                .verifyAddressDetailsText()
                .verifyReviewYourOrderText()
                .inputDescriptionTextArea(textArea)
                .clickPlaceOrderBtn()
                .fillPaymentOrder()
                .clickPayAndConfirmOrder()
                .verifyOrderPlacedSuccessfully()
                .clickDeleteAccount()
                .waitForPageLoaded();
    }

    @Test
    @DisplayName("Test Case 16: Place Order: Login before Checkout")
    @Owner("Kaniet")
    @Tag("Test_Сase_16")
    public void placeOrderLoginBeforeCheckoutTest() {

        var firstUser = UserGenerated.randomUser();
        var blue_top = "Blue Top";
        var men_tShirt = "Men Tshirt";
        var textArea = "Blue Top cringe";

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBtn()
                .waitForPageLoaded()
                .fillName(firstUser.getName())
                .fillEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signUpNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueBtn()
                .verifyLoggedAsUserNameVisible(firstUser.getName())
                .addProductToCart(blue_top)
                .clickContinueShopping()
                .addProductToCart(men_tShirt)
                .clickContinueShopping()
                .switchBetweenSection(CART, CartPage.class)
                .waitForPageLoaded()
                .clickProceedCheckout(CheckoutPage.class)
                .verifyAddressDetailsText()
                .verifyReviewYourOrderText()
                .inputDescriptionTextArea(textArea)
                .clickPlaceOrderBtn()
                .fillPaymentOrder()
                .clickPayAndConfirmOrder()
                .verifyOrderPlacedSuccessfully()
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinue();
    }

    @Test
    @DisplayName("Test Case 17: Remove Products From Cart")
    @Owner("Kaniet")
    @Tag("Test_Сase_17")
    public void removeProductsFromCartTest() {

        var blue_top = "Blue Top";
        var men_tShirt = "Men Tshirt";

        open("", HomePage.class)
                .waitForPageLoaded()
                .addProductToCart(blue_top)
                .clickContinueShopping()
                .addProductToCart(men_tShirt)
                .clickContinueShopping()
                .switchBetweenSection(CART, CartPage.class)
                .waitForPageLoaded()
                .clickDeleteProductFromCart()
                .clickDeleteProductFromCart()
                .verifyThatProductIsRemovedFromTheCart();
    }

    @Test
    @DisplayName("Test Case 18: View Category Products")
    @Owner("Kaniet")
    @Tag("Test_Сase_18")
    public void viewCategoryProductsTest() {

        open("", HomePage.class)
                .verifyLeftSideBarIsVisible()
                .clickWomenCategory()
                .clickWomenSubCategory()
                .waitForPageLoaded()
                .clickMenCategoryAndSubCategory()
                .verifyMenTShirtsProductsHeaderIsVisible();
    }

    @Test
    @DisplayName("Test Case 19: View & Cart Brand Products")
    @Owner("Kaniet")
    @Tag("Test_Сase_19")
    public void viewAndCartBrandsProductsTest() {

        open("products", ProductsPage.class)
                .verifyBrandsOnLeftSideBarAreVisible()
                .waitForPageLoaded()
                .clickBrandByName("Polo")
                .waitForPageLoaded()
                .clickBrandByName("H&M")
                .waitForPageLoaded();
    }

    @Test
    @DisplayName("Test Case 20: Search Products and Verify Cart After Login")
    @Owner("Kaniet")
    @Tag("Test_Сase_20")
    public void searchProductsAndVerifyCartAfterLoginTest() {

        User firstUser = UserGenerated.randomUser();
        SoftAssertions softly = new SoftAssertions();

        var email = firstUser.getEmail();
        var password = firstUser.getPassword();

        var blue_top = "Blue Top";
        var men_tShirt = "Men Tshirt";

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBtn()
                .waitForPageLoaded()
                .fillName(firstUser.getName())
                .fillEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signUpNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueBtn()
                .verifyLoggedAsUserNameVisible(firstUser.getName())
                .clickLogout();

        var cartPage = open("", HomePage.class)
                .switchBetweenSection(PRODUCT, ProductsPage.class)
                .waitForPageLoaded()
                .fillSearchInput(blue_top)
                .clickSearchBtn()
                .verifySearchedProductsHeaderIsVisible()
                .verifyAllSearchedProductsAreVisible(blue_top)
                .addProductToCart(blue_top)
                .clickViewCart();

        step("Проверка выбранных продуктов в корзине", () -> {
            List<String> expectedProducts = List.of(blue_top);
            softly.assertThat(cartPage.verifyProductsInCart())
                    .as("Verify products in cart")
                    .isEqualTo(expectedProducts);
        });

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBtn()
                .waitForPageLoaded()
                .loginUser(email, password, HomePage.class)
                .switchBetweenSection(CART, CartPage.class);

        step("Проверка выбранных продуктов в корзине", () -> {
            List<String> expectedProducts = List.of(blue_top);
            softly.assertThat(cartPage.verifyProductsInCart())
                    .as("Verify products in cart")
                    .isEqualTo(expectedProducts);
        });

        softly.assertAll();
    }
}