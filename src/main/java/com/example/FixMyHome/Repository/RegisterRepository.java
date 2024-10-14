package com.example.FixMyHome.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FixMyHome.Model.RegisterModel;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterModel, Long>{
	RegisterModel getByEmail(String email);

}
