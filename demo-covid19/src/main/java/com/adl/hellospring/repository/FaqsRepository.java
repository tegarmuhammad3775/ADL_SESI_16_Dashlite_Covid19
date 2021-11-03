package com.adl.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adl.hellospring.model.FaqsModel;


@Repository
public interface FaqsRepository extends JpaRepository<FaqsModel, Integer>{

}
