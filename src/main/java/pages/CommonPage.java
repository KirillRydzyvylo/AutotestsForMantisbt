package pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CommonPage extends AbstractPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Подпись [Имя пользователя]
    private static final String USER_NAME_XPATH = "//a[@class='dropdown-toggle']/span";
    // Сайдбар, элемент [Создать задачу]
    private static final String CREATE_TASK_XPATH = "//a[@href='/bugs/bug_report_page.php']";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String>  itemNames= new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public CommonPage() {
        itemNames.put("Создать задачу", CREATE_TASK_XPATH);
    }
    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Происходит проверка имени пользователя
     *
     * @param expectedUserName имя пользователя
     */
    public void checkUserName(String expectedUserName) {
        SelenideElement element = $(this.getBy(USER_NAME_XPATH)).waitUntil(visible,longtime,polling);
        Assert.assertEquals("Проверка имени пользователя", expectedUserName, element.getText());
    }

    /**
     * Происходит нажатие на элемент в меню слева
     *
     * @param itemName имя элемента
     */
    public void clickOnMenuItem(String itemName) {
        $(this.getBy(itemNames.get(itemName))).waitUntil(visible, longtime, polling).click();
    }
}
