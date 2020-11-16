package pages.bugs;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import pages.AbstractPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SignUpPage extends AbstractPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Поле [Пользователь]
    private static final String USERNAME_FIELD_ID = "username";
    // Поле [Электронная почта]
    private static final String EMAIL_FIELD_ID = "email-field";
    // Поле [Капча]
    private static final String CAPTCHA_FIELD_ID = "captcha-field";
    // Кнопка [Зарегистрироваться]
    private static final String SIGN_UP_BUTTON_XPATH = "//form[@id='signup-form']//input[contains(@class,'btn')]";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> fieldNames = new HashMap<>();
    Map<String, String> buttonNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public SignUpPage() {
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.put("Пользователь", USERNAME_FIELD_ID);
        fieldNames.put("Электронная почта", EMAIL_FIELD_ID);
        fieldNames.put("Капча", CAPTCHA_FIELD_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.put("Зарегистрироваться", SIGN_UP_BUTTON_XPATH);
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
     * Происходит заполнение поля.
     *
     * @param fieldName имя заполняемого поля
     * @param value     значение
     */
    public void checkTheFieldValue(String fieldName, String value) {
        this.logFieldNameToFill(fieldName, value);

        SelenideElement field = $(this.getBy(fieldNames.get(fieldName))).waitUntil(visible, longtime, polling);
        field.setValue(value);

        Assert.assertEquals("[ОШИБКА] поле заполнено не верно.", value, field.getValue());
    }

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
