package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FullNameViewModel {

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
