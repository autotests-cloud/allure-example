package qa.guru.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Feature("Работа с задачами")
public class StepsVsPlainTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String USER = "eroshenkoam";
    private final static int ISSUE_NUMBER = 12;

    private final BasicSteps steps = new BasicSteps();

    @Before
    public void initSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    @Test
    @Story("Проверка наличия задач")
    @DisplayName("Проверка наличия Issues в проекте GitHub")
    public void testIssueExists() {
        steps.openMainPage(USER);
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryIssues();
        steps.shouldSeeIssueWithId(ISSUE_NUMBER);
    }

    @Test
    @Story("Проверка наличия задач")
    @DisplayName("Проверка наличия Issues в проекте GitHub")
    public void testIssueExistsWithoutSteps() {
        parameter("User", USER);
        parameter("Repository", REPOSITORY);
        parameter("Issue Number", ISSUE_NUMBER);

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Открываем страницу с репозиторием", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys("eroshenkoam/allure-example");
            $(".header-search-input").submit();
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Открываем страницу с задачами в репозитории", () -> {
            $(withText("Issues")).click();
        });
        step("Проверяем наличие задачи с ID=" + ISSUE_NUMBER, () -> {
            $(withText("#12")).exists();
        });
    }


}
