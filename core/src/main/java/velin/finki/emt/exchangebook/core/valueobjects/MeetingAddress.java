package velin.finki.emt.exchangebook.core.valueobjects;

import lombok.NonNull;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class MeetingAddress extends Address{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "hour", column = @Column(name = "hour")),
            @AttributeOverride(name = "minutes", column = @Column(name = "minutes")),
            @AttributeOverride(name = "period", column = @Column(name = "period"))
    })
    private Time meetingTime;
    public MeetingAddress( @NonNull String address,
                          @NonNull CityName city, @NonNull Time meetingTime) {
        super(address, city);
        this.meetingTime = Objects.requireNonNull(meetingTime, "time of meeting must not be null");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MeetingAddress that = (MeetingAddress) o;
        return Objects.equals(meetingTime, that.meetingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), meetingTime);
    }

    @Override
    public String toString() {
        return "MeetingAddress{" +
                "meetingTime=" + meetingTime +
                '}';
    }
}
