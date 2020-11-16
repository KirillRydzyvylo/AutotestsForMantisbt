package pages.bugs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import pages.AbstractPage;
import pages.CommonPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MyViewPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Кнопка [Зарегистрировать новую учетную запись]
    private static final String SIGN_UP_BUTTON_XPATH = "//a[@href='/bugs/signup_page.php']";
    // Кнопка [Вход]
    private static final String LOGIN_BUTTON_XPATH = "//a[contains(@href,'/bugs/login_page.php')]";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> buttonNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public MyViewPage() {
        buttonNames.put("Зарегистрировать новую учётную запись", SIGN_UP_BUTTON_XPATH);
        buttonNames.put("Вход", LOGIN_BUTTON_XPATH);
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Происходит нажатие на кнопку.
     *
     * @param buttonName имя кнопки
     */
    public void clickOnButton(String buttonName) {
        this.logButtonNameToPress(buttonName);

        $(this.getBy(buttonNames.get(buttonName))).waitUntil(visible,longtime,polling).click();

        this.logPressedButtonName(buttonName);
    }
}
