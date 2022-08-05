package net.intelliboard.next.services.pages;

import com.codeborne.selenide.Condition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DatePicker {
    public static DatePicker init() {
        $x("//div[contains (@class,'flatpickr-calendar')]")
                .shouldBe(Condition.visible);
        return new DatePicker();
    }

    private void setDayOfWeek(LocalDateTime date) {
        String dayOfWeek = date.getDayOfWeek().toString().substring(0, 3);
        $x("//span[contains (@class,'flatpickr-weekday')]").click();
    }

    public DatePicker setDayOfMonth(LocalDateTime date) {
        String dayOfMonth = Integer.toString(date.getDayOfMonth());
        $x("//span[contains (@class,'flatpickr-day')  and (text()='" + dayOfMonth + "')][not( contains (@class,'flatpickr-disabled'))]")
                .click();
        String setDate = $x("//input[contains (@name,'mongoose_cadence_init')]").getAttribute("value");
        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(setDate)).isTrue();
        return this;
    }
}
