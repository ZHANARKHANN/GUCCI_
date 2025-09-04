import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selections.ContactUsPage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ContactUsTest extends BaseWebTest {

    @Test
    @DisplayName("Test Case 6: Contact Us Form")
    @Owner("Aliia")
    @Tag("Test Case 6")
    public void contactUsTest() {

        SoftAssertions softAssertions = new SoftAssertions();

        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection("Contact us", ContactUsPage.class)
                .fillContactUsForm("aliia", "aliia@gmail.com", "subject sum text",
                        "message")
                .clickSubmit()
                .clickAlertAccept();

        step("Проверка присутсвия текста", () -> {
                    softAssertions.assertThat(homePage.detailSubmittedMessage.getText())
                            .as("Check Success Message")
                            .isEqualTo("Success! Your details have been submitted successfully.");
                }
        );

        softAssertions.assertAll();

        homePage.clickHome();
    }
}
