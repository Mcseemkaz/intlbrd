package net.intelliboard.next.services.pages.elements;

import com.codeborne.selenide.Condition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DatePickerElement {
    public static DatePickerElement init() {
        $x("//div[contains (@class,'flatpickr-calendar')]")
                .shouldBe(Condition.visible);
        return new DatePickerElement();
    }

    private void setDayOfWeek(LocalDateTime date) {
        String dayOfWeek = date.getDayOfWeek().toString().substring(0, 3);
        $x("//span[contains (@class,'flatpickr-weekday')]").click();
    }

    public DatePickerElement setDayOfMonth(LocalDateTime dateFrom, LocalDateTime dateTo) {
        String dayOfMonthFrom = Integer.toString(dateFrom.getDayOfMonth());
        String dayOfMonthTo = Integer.toString(dateTo.getDayOfMonth());
        $x("//span[contains (@class,'flatpickr-day')  and (text()='" + dayOfMonthFrom + "')][not( contains (@class,'nextMonthDay'))][not(contains (@class, 'prevMonthDay'))]")
                .click();
        $x("//span[contains (@class,'flatpickr-day')  and (text()='" + dayOfMonthTo + "')][not( contains (@class,'nextMonthDay'))][not(contains (@class, 'prevMonthDay'))]")
                .click();
        return this;
    }

    public DatePickerElement setDayOfMonth(LocalDateTime date) {

        String dayOfMonthFrom = Integer.toString(date.getDayOfMonth());

        $x("//span[contains (@class,'flatpickr-day')  and (text()='" + dayOfMonthFrom + "')][not( contains (@class,'nextMonthDay'))][not(contains (@class, 'prevMonthDay'))]")
                .click();

        String setDate = $x("//input[contains (@class, 'flatpickr-input') and @name='mongoose_cadence_init_processing_date']").getAttribute("value");
        assertThat(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(setDate)).isTrue();
        return this;
    }
}
