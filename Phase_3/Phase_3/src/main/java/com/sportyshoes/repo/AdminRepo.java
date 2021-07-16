package com.sportyshoes.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Admin;

@Repository
public interface AdminRepo extends CrudRepository<Admin, String> {

}
