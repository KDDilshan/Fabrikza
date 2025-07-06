package com.kavindu.fabrikza.payment.Repository;

import com.kavindu.fabrikza.payment.models.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDeatilsRepository extends JpaRepository<ShippingDetails,Integer> {
}
