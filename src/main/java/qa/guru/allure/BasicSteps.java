package qa.guru.allure;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;

public class BasicSteps {

    @Step("Открываем главную страницу")
    public void openMainPage(String user) {
        parameter("user", user);
        open("https://github.com");
    }

    @Step("Открываем страницу с репозиторием")
    public void searchForRepository(String name) {
        parameter("Repository", name);
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(name);
        $(".header-search-input").submit();
        $(By.linkText(name)).click();
    }

    @Step("Открываем страницу с задачами в репозитории")
    public void openRepositoryIssues() {
        $(withText("Issues")).click();
    }

    @Step("Проверяем наличие задачи c id")
    public void shouldSeeIssueWithId(int number) {
        parameter("Issue Number", number);
        $(withText("#" + number)).exists();
    }

}
