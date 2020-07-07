package velin.finki.emt.exchangebook.core.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.istack.NotNull;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.ValueObject;
import velin.finki.emt.exchangebook.core.enums.PeriodOfTheDay;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class Time implements ValueObject {
    @Column(name="hour")
    private int hour;

    @Column(name="minutes")
    private int minutes;

    @Column(name = "period", nullable = false)
    @Enumerated(EnumType.STRING)
    private PeriodOfTheDay period;

    @JsonCreator
    public Time(@NonNull int hour, @NotNull int minutes, @NotNull PeriodOfTheDay period ) {
        if(!(Math.max(1, hour) == Math.min(hour, 12)))
            throw new IllegalArgumentException("Hours must be in range (1,12)");
        if(!(Math.max(0, hour) == Math.min(hour, 59)))
            throw new IllegalArgumentException("Hours must be in range (0,59)");
        this.hour = Objects.requireNonNull(hour, "hours must not be null");
        this.minutes = Objects.requireNonNull(minutes, "minutes must not be null");
        this.period = Objects.requireNonNull(period, "period must not be null");
    }

    @Override
    public String toString() {
        return "Time{" +
                "hours=" + hour +
                ", minutes=" + minutes +
                ", period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour &&
                minutes == time.minutes &&
                period == time.period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minutes, period);
    }
}
