package pages.bugs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.CommonPage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BugReportPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Разворачивающееся поле [Категория]
    private static final String CATEGORY_FIELD_ID = "category_id";
    // Разворачивающееся поле [Категория] вариант [Api Rest]
    private static final String CATEGORY_API_REST_OPTION_XPATH = "//select[@id='category_id']/option[@value='136']";
    // Разворачивающееся поле [Воспроизводимость]
    private static final String REPRODUCIBILITY_FIELD_ID = "reproducibility";
    // Разворачивающееся поле [Воспроизводимость] вариант [Иногда]
    private static final String REPRODUCIBILITY_SOMETIMES_OPTION_XPATH = "//select[@id='reproducibility']/option[@value='30']";
    // Разворачивающееся поле [Влияние]
    private static final String SEVERITY_FIELD_ID = "severity";
    // Разворачивающееся поле [Влияние] вариант [Малое]
    private static final String SEVERITY_LITTLE_OPTION_XPATH = "//select[@id='severity']/option[@value='50']";
    // Разворачивающееся поле [Приоритет]
    private static final String PRIORITY_FIELD_ID = "priority";
    // Разворачивающееся поле [Приоритет] вариант [Обычный]
    private static final String PRIORITY_USUAL_OPTION_XPATH = "//select[@id='priority']/option[@value='30']";
    // Разворачивающееся поле [Версия продукта]
    private static final String PRODUCT_VERSION_FIELD_ID = "product_version";
    // Разворачивающееся поле [Версия продукта] вариант [2.0.0]
    private static final String PRODUCT_VERSION_USUAL_2_0_0_XPATH = "//select[@id='product_version']/option[@value='2.0.0']";
    // Поле [Тема]
    private static final String SUMMARY_FIELD_ID = "summary";
    // Поле [Описание]
    private static final String DESCRIPTION_FIELD_ID = "description";
    // Поле [Шаги по воспроизведению]
    private static final String STEP_TO_REPRODUCE_FIELD_ID = "steps_to_reproduce";
    // Поле [Дополнительные сведения]
    private static final String ADDITIONAL_INFO_FIELD_ID = "additional_info";
    // Кнопка [Создать задачу]
    private static final String CREATE_TASK_BUTTON_XPATH = "//input[@value='Создать задачу']";
    // Кнопка [Создать задачу]
    private static final String VIEW_THE_TASK_BUTTON_XPATH = "//input[contains(@value,'Посмотреть созданную задачу')]";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> optionNames = new HashMap<>();
    Map<String, String> selectableFieldNames = new HashMap<>();
    Map<String, String> buttonNames = new HashMap<>();
    Map<String, String> fieldNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public BugReportPage() {
        //--------------------------------------------------------------------------------------------------------------
        selectableFieldNames.put("Категория", CATEGORY_FIELD_ID);
        selectableFieldNames.put("Воспроизводимость", REPRODUCIBILITY_FIELD_ID);
        selectableFieldNames.put("Влияние", SEVERITY_FIELD_ID);
        selectableFieldNames.put("Приоритет", PRIORITY_FIELD_ID);
        selectableFieldNames.put("Версия продукта", PRODUCT_VERSION_FIELD_ID);
        //--------------------------------------------------------------------------------------------------------------
        optionNames.put("api rest", CATEGORY_API_REST_OPTION_XPATH);
        optionNames.put("иногда", REPRODUCIBILITY_SOMETIMES_OPTION_XPATH);
        optionNames.put("малое", SEVERITY_LITTLE_OPTION_XPATH);
        optionNames.put("обычный", PRIORITY_USUAL_OPTION_XPATH);
        optionNames.put("2.0.0", PRODUCT_VERSION_USUAL_2_0_0_XPATH);
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.put("Тема", SUMMARY_FIELD_ID);
        fieldNames.put("Описание", DESCRIPTION_FIELD_ID);
        fieldNames.put("Шаги по воспроизведению", STEP_TO_REPRODUCE_FIELD_ID);
        fieldNames.put("Дополнительные сведения", ADDITIONAL_INFO_FIELD_ID);
        //--------------------------------------------------------------------------------------------------------------
        buttonNames.put("Создать задачу", CREATE_TASK_BUTTON_XPATH);
        buttonNames.put("Посмотреть созданную задачу", VIEW_THE_TASK_BUTTON_XPATH);
        //--------------------------------------------------------------------------------------------------------------
    }
    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Происходит выбор варианта значения для поля с выпадающим списком
     *
     * @param selectableFieldName имя поля
     * @param optionName          имя варианта значения
     */
    public BugReportPage selectOptionValue(String selectableFieldName, String optionName) {
        this.logFieldNameToFill(selectableFieldNames.get(selectableFieldName), optionName);

        $(this.getBy(selectableFieldNames.get(selectableFieldName))).click();
        $(this.getBy(optionNames.get(optionName))).waitUntil(visible, longtime, polling).click();
        config.setParameter(selectableFieldName, optionName);

        return this;
    }

    /**
     * Происходит заполнение поля.
     *
     * @param fieldName имя заполняемого поля
     * @param value     значение
     */
    public BugReportPage fillTheField(String fieldName, String value) {
        this.logFieldNameToFill(fieldName, value);

        $(this.getBy(fieldNames.get(fieldName))).waitUntil(visible, longtime, polling).setValue(value);
        config.setParameter(fieldName, value);

        return this;
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



