package com.sportyshoes.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepo extends CrudRepository<PurchaseOrder, Integer> {


}
