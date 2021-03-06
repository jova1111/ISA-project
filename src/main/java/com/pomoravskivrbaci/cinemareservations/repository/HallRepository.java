package com.pomoravskivrbaci.cinemareservations.repository;


import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Institution;

public interface HallRepository extends PagingAndSortingRepository<Hall,Long> {
	
	Hall findById(Long id);
	List<Hall> findByInstitutionId(Long id);
	List<Hall> findByProjections_id(Long id);
}
