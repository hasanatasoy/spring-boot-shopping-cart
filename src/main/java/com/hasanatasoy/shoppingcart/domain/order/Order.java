package com.hasanatasoy.shoppingcart.domain.order;

import com.hasanatasoy.shoppingcart.domain.address.Address;
import com.hasanatasoy.shoppingcart.domain.cart.Cart;
import com.hasanatasoy.shoppingcart.domain.order.payment.PaymentType;
import com.hasanatasoy.shoppingcart.domain.order.shipment.Shipment;
import com.hasanatasoy.shoppingcart.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Address orderAddress;
    @OneToOne
    private Address invoiceAddress;
    @OneToOne
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne
    private Shipment shipment;
    private PaymentType paymentType;
}
