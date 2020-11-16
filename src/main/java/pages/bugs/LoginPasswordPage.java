package pages.bugs;

import com.codeborne.selenide.SelenideElement;
import pages.AbstractPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPasswordPage extends AbstractPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Поле [Пароль]
    private static final String PASSWORD_FIELD_ID = "password";
    // Флажок [Доступ к этой сессии будет только с ...]
    private static final String SECURE_SESSION_CHECKBOX_ID = "secure-session";
    // Подпись к флажку [Доступ к этой сессии будет только с ...]
    private static final String SPAN_SECURE_SESSION_CHECKBOX_ID = "//input[@id='secure-session']/../span";
    // Кнопка [Вход]
    private static final String LOGIN_BUTTON_XPATH = "//input[@value='Вход']";


    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> fieldNames = new HashMap<>();
    Map<String, String> buttonNames = new HashMap<>();
    Map<String, String> checkBoxNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public LoginPasswordPage() {
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.put("Пароль", PASSWORD_FIELD_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.put("Вход", LOGIN_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        checkBoxNames.put("Доступ к этой сессии будет только с ...", SECURE_SESSION_CHECKBOX_ID);
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

    /**
     * Происходит сброс флажка.
     *
     * @param checkBoxName имя флажка
     */
    public void resetCheckBox(String checkBoxName) {

        SelenideElement element = $(this.getBy(checkBoxNames.get(checkBoxName))).waitUntil(exist,longtime,polling);
        if(element.isSelected()) $(this.getBy(SPAN_SECURE_SESSION_CHECKBOX_ID)).click();

    }
}
