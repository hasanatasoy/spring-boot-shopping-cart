package com.hasanatasoy.shoppingcart.domain.user.info;

import com.hasanatasoy.shoppingcart.domain.address.Address;
import com.hasanatasoy.shoppingcart.domain.user.UserGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 30)
    private String name;
    @Size(min = 1, max = 20)
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Size(min = 8, max = 15)
    private String telephoneNumber;
    @OneToMany
    private List<Address> addresses;
    private UserGender userGender;

    public UserInfo(UserGender userGender){
        this.userGender = userGender;
    }

}
