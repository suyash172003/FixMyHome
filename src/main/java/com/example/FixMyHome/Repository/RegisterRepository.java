package com.example.FixMyHome.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FixMyHome.Model.RegisterModel;

public interface RegisterRepository extends JpaRepository<RegisterModel, Long>{
	RegisterModel getByEmail(String email);

}
