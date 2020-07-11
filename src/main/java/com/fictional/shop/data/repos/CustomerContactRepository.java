package com.fictional.shop.data.repos;

import org.springframework.data.repository.CrudRepository;

import com.fictional.shop.domain.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

}
