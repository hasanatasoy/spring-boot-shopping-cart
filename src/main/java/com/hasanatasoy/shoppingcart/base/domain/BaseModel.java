package com.hasanatasoy.shoppingcart.base.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
