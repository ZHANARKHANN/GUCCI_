import com.gucci.entities.UserGenerated;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.ProductsPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.gucci.data.Sections.PRODUCT;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class AddReviewOnProductTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 21: Add review on product")
    @Owner("Ulugbek")
    @Tag("Test Case 21")
    public void addReviewOnProduct() {

        var productName = "Men Tshirt";
        var user = UserGenerated.randomUser();

        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(PRODUCT, ProductsPage.class)
                .waitForPageLoaded()
                .clickViewProduct(productName)
                .waitForPageLoaded()
                .fillYourName(user.getName())
                .fillEmailAddress(user.getEmail())
                .fillAddReviewHere(user.getAddress1())
                .clickSubmitBtn()
                .verifyThankYouForYourReview();
    }
}
