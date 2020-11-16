package pages.bugs;

import pages.AbstractPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends AbstractPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Поле [Пользователь]
    private static final String USERNAME_FIELD_ID = "username";
    // Кнопка [Вход]
    private static final String LOGIN_BUTTON_XPATH = "//input[@value='Вход']";


    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> fieldNames = new HashMap<>();
    Map<String, String> buttonNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public LoginPage() {
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.put("Пользователь", USERNAME_FIELD_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.put("Вход", LOGIN_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Происходит заполнение поля.
     *
     * @param fieldName имя заполняемого поля
     * @param value     значение
     */
    public void fillTheField(String fieldName, String value) {
        this.logFieldNameToFill(fieldName, value);

        $(this.getBy(fieldNames.get(fieldName))).waitUntil(visible, longtime, polling).setValue(value);
    }

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
