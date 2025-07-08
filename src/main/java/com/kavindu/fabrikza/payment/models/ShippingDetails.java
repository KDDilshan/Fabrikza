package com.kavindu.fabrikza.payment.models;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "add Shipping Address")
    private String shippingAddress;

    @NotNull(message = "enter phone no")
    private String phoneNo;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private AppUser appUser;
}
