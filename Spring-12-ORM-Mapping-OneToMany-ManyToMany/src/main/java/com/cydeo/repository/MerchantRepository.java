package com.cydeo.repository;

import com.cydeo.model.Merchant;
import com.cydeo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {
}
