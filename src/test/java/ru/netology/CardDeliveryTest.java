package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Value;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.DataFormatException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    public static String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    @Test
    public void shouldOrderCreated() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(calendar.getTime());
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Казань");
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+79991892310");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.visible, Duration.ofSeconds(15));

    }
}
