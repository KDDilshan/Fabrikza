package com.kavindu.fabrikza.payment.models;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @UuidGenerator
    private UUID id;

    private Date date;

    private String status;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private ShippingDetails  shippingDetails;

}
