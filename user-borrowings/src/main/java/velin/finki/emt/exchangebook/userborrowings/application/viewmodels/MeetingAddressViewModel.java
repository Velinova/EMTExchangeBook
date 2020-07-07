package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import lombok.Data;
import velin.finki.emt.exchangebook.core.valueobjects.CityName;
import velin.finki.emt.exchangebook.core.valueobjects.Time;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MeetingAddressViewModel implements Serializable {


    @NotEmpty
    private String address;
    @NotNull
    private CityName city;
    @NotNull
    private Time meetingTime;

    public String getAddress(){
        return address;
    }
    public CityName getCity(){
        return city;
    }
    public Time getTime(){
        return meetingTime;
    }

}

