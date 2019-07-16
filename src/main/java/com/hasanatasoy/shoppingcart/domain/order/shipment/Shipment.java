package com.hasanatasoy.shoppingcart.domain.order.shipment;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "shipmentgenerator", sequenceName = "shipment_seq")
@Getter
@Setter
public class Shipment extends BaseModel {

    private ShipmentType shipmentType;
    private int shipmentCost;
}
