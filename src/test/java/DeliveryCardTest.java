import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldDeliveryCardOrder() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Кемерово");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Павел-К");
        $("[data-test-id=phone] input").setValue("+79238456245");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + dateOfMeeting));
    }
}