import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.ProductsPage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;


@Tag(Tags.WEB)
public class SearchProductTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 9: Search Product")
    @Owner("Aliia")
    @Tag("Test_Case_9")
    public void searchProductTest() {

        SoftAssertions softAssertions = new SoftAssertions();

        var productName = "Blue Top";

        var productPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection("Products", ProductsPage.class)
                .waitForPageLoaded()
                .fillSearchInput(productName)
                .clickSearchBtn();

        step("Verify that detail detail is visible: product name, category, price, availability, condition, brand", () -> {
            softAssertions.assertThat(productPage.searchedProductsHeader.getText().toLowerCase())
                    .as("Verify search product header")
                    .isEqualTo("searched products");
        });

        step("Verify all the products related to search are visible", () -> {
            softAssertions.assertThat(productPage.searchProductList)
                    .as("Verify searchProductList")
                    .allMatch(SelenideElement::isDisplayed);
        });

        softAssertions.assertAll();
    }
}