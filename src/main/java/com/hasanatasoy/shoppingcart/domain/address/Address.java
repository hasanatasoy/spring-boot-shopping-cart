package com.hasanatasoy.shoppingcart.domain.address;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "addressgenerator", sequenceName = "address_seq")
@Getter
@Setter
public class Address extends BaseModel {


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
