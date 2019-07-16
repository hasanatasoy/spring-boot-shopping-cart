package com.hasanatasoy.shoppingcart.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean isDeleted = false;
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    public void onCreate(){
        createdAt = new Date();
    }

    @PostPersist
    public void onUpdate(){
        updatedAt = new Date();
    }

}
