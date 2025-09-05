import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)

public class AddToCartFromRecommendedItemsTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 22: Add to cart from Recommended items")
    @Owner("Ulugbek")
    @Tag("Test_Case_22")
    public void addToCartFromRecommendedItems() {

        var productName = "Blue Top";

        open("", HomePage.class)
                .scrollToRecommendedItems()
                .verifyRecommendedItems()
                .addProductToCart(productName)
                .clickViewCart()
                .verifyProductInCart(productName);
    }
}