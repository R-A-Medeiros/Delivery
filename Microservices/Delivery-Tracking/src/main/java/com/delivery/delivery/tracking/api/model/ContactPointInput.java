package com.delivery.delivery.tracking.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactPointInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    private String complement;

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotBlank
    private String phone;
}
