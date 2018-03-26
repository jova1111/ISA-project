package com.pomoravskivrbaci.cinemareservations.controller;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editInstitutionDescription(@PathVariable("id")Long id, @RequestBody Institution inst) {
        institutionService.setInstitutionInfoById(id, inst.getName(), inst.getAddress(), inst.getDescription());
        return new ResponseEntity(HttpStatus.OK);
    }

}