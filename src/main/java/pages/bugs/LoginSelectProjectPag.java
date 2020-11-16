package pages.bugs;

import com.codeborne.selenide.SelenideElement;
import pages.AbstractPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginSelectProjectPag extends AbstractPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Кнопка [Выбрать проект]
    private static final String LOGIN_BUTTON_XPATH = "//input[@value='Выбрать проект']";


    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> buttonNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public LoginSelectProjectPag() {
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.put("Выбрать проект", LOGIN_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
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

        $(this.getBy(buttonNames.get(buttonName))).waitUntil(visible, longtime, polling).click();

        this.logPressedButtonName(buttonName);
    }
}
