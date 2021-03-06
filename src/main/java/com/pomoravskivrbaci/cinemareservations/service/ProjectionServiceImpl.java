package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import com.pomoravskivrbaci.cinemareservations.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository projectionRepository;

	@Override
	public Projection findById(Long id) {
		return projectionRepository.findById(id);
	}

	@Override
	public List<Projection> findByRepertoires_id(Long id) {
		// TODO Auto-generated method stub
		return projectionRepository.findByRepertoires_id(id).stream()
				.filter(projection -> !projection.getDeleted())
				.collect(Collectors.toList());
	}

	@Override
	public void setProjectionInfoById(Long id, String name, String actors, String genre, String description, String directorName, Double price) {
		projectionRepository.setProjectionInfoById(id, name, actors, genre, description, directorName, price);
	}
	@Override
	public Projection findByPeriods(Period period) {
		// TODO Auto-generated method stub
		return projectionRepository.findByPeriods(period);
	}

	@Override
	public Projection saveOrUpdate(Projection projection) {
		return projectionRepository.save(projection);
	}

	@Override
	public void deleteById(Long id) {
		projectionRepository.delete(id);
	}


}
