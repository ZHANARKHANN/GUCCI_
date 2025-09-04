import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.TestCasesPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class TestCasesTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 7: Verify Test Cases Page")
    @Owner("Aliia")
    @Tag("Test Case 7")
    public void testCasesTest() {

        open("", HomePage.class)
                .switchBetweenSection("Test Cases", TestCasesPage.class)
                .waitForPageLoaded();
    }
}
