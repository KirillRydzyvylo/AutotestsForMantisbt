package pages.bugs;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import pages.CommonPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ViewPage extends CommonPage {
    /*******************************************************************************************************************
     * Локаторы элементов страницы.
     ******************************************************************************************************************/
    // Разворачивающееся поле [Категория]
    private static final String CATEGORY_FIELD_XPATH = "//td[@class='bug-category']";
    // Разворачивающееся поле [Воспроизводимость]
    private static final String REPRODUCIBILITY_FIELD_XPATH = "//td[@class='bug-reproducibility']";
    // Разворачивающееся поле [Влияние]
    private static final String SEVERITY_FIELD_XPATH = "//td[@class='bug-severity']";
    // Разворачивающееся поле [Приоритет]
    private static final String PRIORITY_FIELD_XPATH = "//td[@class='bug-priority']";
    // Разворачивающееся поле [Версия продукта]
    private static final String PRODUCT_VERSION_FIELD_XPATH = "//td[@class='bug-product-version']";
    // Поле [Тема]
    private static final String SUMMARY_FIELD_XPATH = "//td[@class='bug-summary']";
    // Поле [Описание]
    private static final String DESCRIPTION_FIELD_XPATH = "//td[@class='bug-description']";
    // Поле [Шаги по воспроизведению]
    private static final String STEP_TO_REPRODUCE_FIELD_XPATH = "//td[@class='bug-steps-to-reproduce']";
    // Поле [Дополнительные сведения]
    private static final String ADDITIONAL_INFO_FIELD_XPATH = "//td[@class='bug-additional-information']";

    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    Map<String, String> fieldNames = new HashMap<>();

    /*******************************************************************************************************************
     *  Конструктор класса.
     ******************************************************************************************************************/
    public ViewPage() {
        //--------------------------------------------------------------------------------------------------------------
        fieldNames.put("Категория", CATEGORY_FIELD_XPATH);
        fieldNames.put("Воспроизводимость", REPRODUCIBILITY_FIELD_XPATH);
        fieldNames.put("Влияние", SEVERITY_FIELD_XPATH);
        fieldNames.put("Приоритет", PRIORITY_FIELD_XPATH);
        fieldNames.put("Версия продукта", PRODUCT_VERSION_FIELD_XPATH);
        fieldNames.put("Тема", SUMMARY_FIELD_XPATH);
        fieldNames.put("Описание", DESCRIPTION_FIELD_XPATH);
        fieldNames.put("Шаги по воспроизведению", STEP_TO_REPRODUCE_FIELD_XPATH);
        fieldNames.put("Дополнительные сведения", ADDITIONAL_INFO_FIELD_XPATH);
        //--------------------------------------------------------------------------------------------------------------

    }
    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Проверяет значение поля
     *
     * @param fieldName     имя поля
     * @param expectedValue ожидаемое значение
     */
    public ViewPage checkFieldValue(String fieldName, String expectedValue) {
        String message = String.format("[ОШИБКА] Поле [%s] не содержит ожидаемое значение.", fieldName);
        SelenideElement element = $(this.getBy(fieldNames.get(fieldName))).waitUntil(visible, longtime * 10, polling);
        Assert.assertTrue(message, element.getText().contains(expectedValue));

        return this;
    }

}

