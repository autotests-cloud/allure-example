package qa.guru.allure;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Feature("Работа с проектами")
public class GithubProjectTest {

    @Test
    @Story("Проверка наличия проекта")
    @DisplayName("Проверка наличия созданного проекта GitHub")
    public void testCreatedProjectExists() {
        assertEquals(true, true);
    }

    @Test
    @Story("Проверка наличия проекта")
    @DisplayName("Проверка наличия дефолтного проекта GitHub")
    public void testDefaultProjectExists() {
        assertEquals(true, false);
    }

}
