package com.thttp.sommelierapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thttp.sommelierapi.model.Vinho;

public interface VinhoRepository extends JpaRepository<Vinho, Long>{

}
