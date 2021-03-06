package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;
import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import com.pomoravskivrbaci.cinemareservations.repository.InstitutionRepository;

@Service
public class InstitutionServiceImpl implements InstitutionService {

	@Autowired
	private InstitutionRepository institutionRepositroy;
	
	@Override
	public List<Institution> findByType(InstitutionType type) {
		// TODO Auto-generated method stub
		return institutionRepositroy.findByType(type);
	}

	@Override
	public Institution findById(Long id) {
		return institutionRepositroy.findById(id);
	}

	@Override
	public Institution findByName(String name) {
		// TODO Auto-generated method stub
		return institutionRepositroy.findByName(name);
	}

	@Override
	public List<Institution> findAll() {
		return institutionRepositroy.findAll();
	}

	@Override
	public void setInstitutionInfoById(Long id, String name, String address, String description) {
		institutionRepositroy.setInstitutionInfoById(id, name, address, description);
	}

	@Override
	public Institution saveOrUpdate(Institution institution) {
		return institutionRepositroy.save(institution);
	}

	@Override
	public Repertoire findByRepertoire(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
