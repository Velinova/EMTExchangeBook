package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import lombok.Data;
import velin.finki.emt.exchangebook.core.enums.PeriodOfTheDay;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class TimeViewModel {
    @NotNull
    @Min(0)
    @Max(11)
    private int hour;

    @NotNull
    @Min(0)
    @Max(59)
    private int minutes;

    @NotNull
    private PeriodOfTheDay period;
}
