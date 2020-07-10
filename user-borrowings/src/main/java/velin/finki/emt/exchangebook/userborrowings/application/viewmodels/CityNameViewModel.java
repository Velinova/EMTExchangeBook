package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CityNameViewModel {
    @NotEmpty
    private String name;

    public String getName(){
        return name;
    }
}
