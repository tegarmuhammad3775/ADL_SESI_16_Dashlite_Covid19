package com.adl.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adl.hellospring.model.PreventionModel;

@Repository
public interface PreventionRepository extends JpaRepository<PreventionModel, Integer>{

}
