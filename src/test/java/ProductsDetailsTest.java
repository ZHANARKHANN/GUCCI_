import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ProductsDetailsTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 13: Verify Product quantity in Cart")
    @Owner("Aidai")
    @Tag("Test Case 13")
    public void verifyProductQuantityInCartTest() {

        var productName = "Blue Top";
        SoftAssertions softly = new SoftAssertions();

        var cartPage = open("", HomePage.class)
                .waitForPageLoaded()
                .clickViewProduct(productName)
                .waitForPageLoaded()
                .increaseQuantity("4")
                .clickAddToCart()
                .clickViewCart()
                .waitForPageLoaded();

        step("Проверка количества товара", () -> {
            softly.assertThat(cartPage.getProductQuantity(productName))
                    .as("Проверка количества товара")
                    .isEqualTo("4");
        });

        softly.assertAll();
    }
}
