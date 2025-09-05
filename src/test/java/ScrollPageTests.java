import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)

public class ScrollPageTests extends BaseWebTest {

    @Test
    @DisplayName("Test Case 25: Scroll up using arrow button and verify top text")
    @Owner("Ulugbek")
    @Tag("Test_Case_25")
    public void verifyScrollUpUsingArrowTest() {

        open("", HomePage.class)
                .waitForPageLoaded()
                .scrollToSubscriptionHeader()
                .verifySubscribeHeader()
                .clickOnArrowAtBottomRightSideToMoveUpward()
                .verifyHeaderIsVisible();
    }

    @Test
    @DisplayName("Test Case 26: Scroll up manually and verify top text")
    @Owner("Ulugbek")
    @Tag("Test_Case_26")
    public void VerifyScrollUpWithoutArrowTest() {

        open("", HomePage.class)
                .waitForPageLoaded()
                .scrollToSubscriptionHeader()
                .verifySubscribeHeader()
                .scrollToHeader()
                .verifyHeaderIsVisible();
    }
}