package pages;

import helpers.ConfigContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class AbstractPage {
    public static final Logger logger = LogManager.getLogger(AbstractPage.class);
    protected ConfigContainer config;
    protected final long longtime = 1000;   // время ожидания длительных операций (миллисекунды)
    protected final long polling = 10;     // интервал проверки выполнения условия (миллисекунды)


    public AbstractPage() {
        this.config = ConfigContainer.getInstance();
    }

    /**
     * Выводит в консоль сообщение о том, что будет нажата кнопка с указанным именем.
     *
     * @param buttonName имя кнопки, которую следует нажать
     */
    protected void logButtonNameToPress(String buttonName) {
        logger.info(String.format("Имя кнопки/ссылки, которую следует нажать: [%s].", buttonName));
    }

    /**
     * Выводит в консоль сообщение о том, что была нажата кнопка с указанным именем.
     *
     * @param buttonName имя нажатой кнопки
     */
    protected void logPressedButtonName(String buttonName) {
        logger.info(String.format("Произведено нажатие кнопки/ссылки: [%s].", buttonName));
    }

    /**
     * Выводит в консоль сообщение о том, что происходит заполнение поля.
     *
     * @param fieldName имя заполняемого поля
     * @param value значение
     */
    protected void logFieldNameToFill(String fieldName, String value) {
        logger.info(String.format("Требуется заполнить поле [%s] значение [%s]", fieldName, value));
    }

    /**
     * Возвращает элемент типа By (By.xpath или By.id) автоматически распознав тип локатора по его содержимому.
     *
     * @param locator локатор элемента
     * @return элемент типа By (By.xpath или By.id)
     */
    protected By getBy(String locator) {
        return locator.contains("/") || locator.contains("[") ? By.xpath(locator) : By.id(locator);
    }
}
