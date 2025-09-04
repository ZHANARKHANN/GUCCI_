import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.ProductsPage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class AllProductAndProductDetailsTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 8: Verify All Products and product detail page")
    @Owner("Aliia")
    @Tag("Test Case 8")
    public void verifyAllProductAndProductDetailTest() {

        SoftAssertions softAssertions = new SoftAssertions();

        var productPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection("Products", ProductsPage.class)
                .waitForPageLoaded();

        step("The products list is visible", () -> {
            softAssertions.assertThat(productPage.products)
                    .as("All elements is visible")
                    .allMatch(SelenideElement::isDisplayed);
        });

        var productDetail = productPage.clickViewProduct("Blue Top");

        step("Verify that detail detail is visible: product name, category, price, availability, condition, brand", () -> {
            softAssertions.assertThat(productDetail.productName.getText())
                    .as("Verify product Name")
                    .isEqualTo("Blue Top");

            softAssertions.assertThat(productDetail.productCategory.getText())
                    .as("Verify productCategory")
                    .isEqualTo("Category: Women > Tops");

            softAssertions.assertThat(productDetail.productPrice.getText())
                    .as("Verify productPrice")
                    .isEqualTo("Rs. 500");

            softAssertions.assertThat(productDetail.productAvailability.isDisplayed())
                    .as("Display Availability")
                    .isEqualTo(true);

            softAssertions.assertThat(productDetail.productCondition.isDisplayed())
                    .as("Display productCondition")
                    .isEqualTo(true);

            softAssertions.assertThat(productDetail.productBrand.isDisplayed())
                    .as("Display productBrand")
                    .isEqualTo(true);
        });

        softAssertions.assertAll();
    }
}
