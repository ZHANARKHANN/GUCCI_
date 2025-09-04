import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.CartPage;
import io.qameta.allure.Owner;
import net.datafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class CartTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 11: Verify Subscription in Cart page ")
    @Owner("Aidai")
    @Tag("Test_Case_11")
    public void verifySubscriptionInCartTest() {

        Faker faker = new Faker();
        SoftAssertions softly = new SoftAssertions();
        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .clickCartBtn()
                .inputEmail(faker.internet().emailAddress());

        step("Проверка присутствия текста Subscription ", () -> {
                    softly.assertThat(homePage.subscriptionText.getText().toLowerCase())
                            .as("verify subscription text")
                            .isEqualTo("subscription");
                }
        );
        step("Проверка присутствия текста you have been successfully subscribed!", () -> {
                    softly.assertThat(homePage.subscribedSuccess.getText())
                            .as("verify you have been successfully subscribed")
                            .isEqualTo("You have been successfully subscribed!");
                });

        softly.assertAll();
    }

    @Test
    @DisplayName("Test Case 12: Add Products in Cart")
    @Owner("Aidai")
    @Tag("Test_Case_12")
    public void verifyCartDetailsTest() {

        SoftAssertions softly = new SoftAssertions();
        var product1 = "Sleeveless Dress";
        var product2 = "Men Tshirt";
        List<String> expectedPrices = List.of("Rs. 1000", "Rs. 400");
        List<String> expectedQuantities = List.of("1", "1");
        List<String> expectedTotals = List.of("Rs. 1000", "Rs. 400");

        CartPage cartPage = open("", HomePage.class)
                .waitForPageLoaded()
                .addProductToCart(product1)
                .clickContinueShopping()
                .addProductToCart(product2)
                .clickViewCart();

        step("Проверка выбранных продуктов в корзине", () -> {
            List<String> expectedProducts = List.of(product1, product2);
            softly.assertThat(cartPage.verifyProductsInCart())
                    .as("Verify products in cart")
                    .containsExactlyInAnyOrderElementsOf(expectedProducts);

            softly.assertThat(cartPage.getProductPrices())
                    .as("Verify product prices")
                    .containsExactlyInAnyOrderElementsOf(expectedPrices);

            softly.assertThat(cartPage.getProductQuantities())
                    .as("Verify product quantities")
                    .containsExactlyInAnyOrderElementsOf(expectedQuantities);

            softly.assertThat(cartPage.getProductTotals())
                    .as("Verify product totals")
                    .containsExactlyInAnyOrderElementsOf(expectedTotals);
        });

        softly.assertAll();
    }
}