import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldTestV1() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Кемерово");
//        $("[data-test-id=date] input").setValue("14.10.2021");
        $("#root div span button").click();
        Calendar calendar = new GregorianCalendar();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        $(byText(String.valueOf(dayOfMonth+3))).click();
        $("[data-test-id=name] input").setValue("Павел-К");
        $("[data-test-id=phone] input").setValue("+79238456245");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забрнировать")).click();
//        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $(withText("Успешно!")).shouldBe(exactText("Успешно!"), Duration.ofSeconds(11));
        $(withText("Встреча успешно забронирована на")).shouldHave(exactText("Встреча успешно забронирована на 14.10.2021"));
    }
}