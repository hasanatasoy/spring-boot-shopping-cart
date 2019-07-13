package com.hasanatasoy.shoppingcart.domain.address;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Address")
@Getter
@Setter
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    @Size(min = 10)
    private String detailedAddress;
    @NotNull
    private String neighbourhood;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private boolean isDefaultAddress;

}
