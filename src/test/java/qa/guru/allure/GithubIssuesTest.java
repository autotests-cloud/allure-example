package qa.guru.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import io.qameta.allure.selenide.AllureSelenide;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Работа с задачами")
public class GithubIssuesTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";

    private ApiSteps apiSteps = new ApiSteps();
    private BasicSteps basicSteps = new BasicSteps();

    @Before
    public void initSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    @Before
    public void initGithubClient() {
    }

    @Test
    @Story("Проверка наличия задач")
    @DisplayName("Проверка наличия Issues в проекте GitHub")
    public void testIssueExistsWithoutSteps() throws Exception {
        final Issue issue = apiSteps.createIssue(
                "eroshenkoam",
                "allure-example",
                "hello from qa.guru",
                "ping-pong"
        );
        basicSteps.openMainPage("eroshenkoam");
        basicSteps.searchForRepository(REPOSITORY);
        basicSteps.openRepositoryIssues();
        basicSteps.shouldSeeIssueWithId(issue.getNumber());
    }

}
