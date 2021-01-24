package com.omkaaij.omkaaij_nl.data.dto;


;
import com.omkaaij.omkaaij_nl.model.Person;
import com.omkaaij.omkaaij_nl.model.Visitor;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDTO implements Serializable {

    public String passwordConfirmation;
    private Visitor visitor;
    private Person person;


}
