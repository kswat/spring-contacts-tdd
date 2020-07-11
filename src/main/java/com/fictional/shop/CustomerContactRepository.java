package com.fictional.shop;

import org.springframework.data.repository.CrudRepository;

import com.fictional.shop.domain.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

}
