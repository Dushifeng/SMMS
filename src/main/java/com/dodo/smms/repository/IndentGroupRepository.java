package com.dodo.smms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dodo.smms.entity.IndentGroup;
import com.dodo.smms.entity.SuperMarket;

public interface IndentGroupRepository extends JpaRepository<IndentGroup, Integer>{
	List<IndentGroup> findBySm(SuperMarket sm);
}
