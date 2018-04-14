package com.pomoravskivrbaci.cinemareservations.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionRating;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.service.FanZoneService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionRatingService;
import com.pomoravskivrbaci.cinemareservations.service.InstitutionService;
import com.pomoravskivrbaci.cinemareservations.service.UserService;
//github.com/jova1111/ISA-project.git

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired

    private FanZoneService fanzoneService;
    
    @Autowired
    private UserService userService;
    @Autowired
    private InstitutionRatingService institutionRatingService;

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    private ResponseEntity editInstitutionDescription(@PathVariable("id")Long id, @RequestBody Institution inst) {
        institutionService.setInstitutionInfoById(id, inst.getName(), inst.getAddress(), inst.getDescription());
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value="/create", method = RequestMethod.GET)
    private String create(HttpServletRequest request) {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
    	return  "forward:/createinstitution.jsp";
    }
    @RequestMapping(value="/createInstitution", method = RequestMethod.POST)
    private ResponseEntity<Institution> createInstitution(@RequestBody Institution inst) {
        System.out.println("Pogodio");
    	institutionService.saveOrUpdate(inst);
    	FanZone fz = new FanZone(inst.getId(),inst.getName()+" Fanzone ");
    	fanzoneService.save(fz);
        return new ResponseEntity<Institution>(inst,HttpStatus.OK);
    }
    
    @RequestMapping(value="/users", method = RequestMethod.GET)
    private String users(HttpServletRequest request) {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
    	return  "forward:/allusers.jsp";
    }
    @RequestMapping(value="/insertFanzoneAdmins/{id}", method = RequestMethod.PATCH)
    private ResponseEntity<FanZone> insertFanzoneAdmins(@RequestBody List<Long> ids,@PathVariable("id") Long fzid){
    	FanZone fz = fanzoneService.findFanZoneById(fzid);
    	
    	for(Long id : ids){
    		System.out.println("id: "+id);
    		User user = userService.findUserById(id);
    		fz.getFanZoneAdmins().add(user);
    	}
    	fanzoneService.save(fz);
    	return new ResponseEntity<FanZone>(fz,HttpStatus.OK);
    }
    
    @RequestMapping(value="/insertInstitutionAdmins/{id}", method = RequestMethod.PATCH)
    private ResponseEntity<Institution> insertInstitutionAdmins(@RequestBody List<Long> ids,@PathVariable("id") Long instid){
    	Institution inst = institutionService.findById(instid);
    	
    	for(Long id : ids){
    		System.out.println("id: "+id);
    		User user = userService.findUserById(id);
    		inst.getAdmins().add(user);
    	}
    	institutionService.saveOrUpdate(inst);
    	return new ResponseEntity<Institution>(inst,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    private String showInstitutionProfile(@PathVariable("id")Long id, HttpServletRequest request) {
        Institution institution = institutionService.findById(id);
        request.setAttribute("institution", institution);
        return "forward:/institution_profile.jsp";
    }

    @RequestMapping(value="/{id}/rate", method = RequestMethod.POST)
    private ResponseEntity rate(@PathVariable("id")Long id, @RequestBody InstitutionRating institutionRating, HttpSession session) {
        User loggedUser = (User)session.getAttribute("loggedUser");
        if(loggedUser == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        for(InstitutionRating instRating : loggedUser.getInstitutionRatings()) {
            if(instRating.getInstitution().getId().equals(id)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        Institution institution = institutionService.findById(id);
        institutionRating.setInstitution(institution);
        institution.addRating(institutionRating);
        institutionRating.setUser(loggedUser);
        loggedUser.addInstitutionRating(institutionRating);
        institutionRatingService.saveOrUpdate(institutionRating);
        return new ResponseEntity(HttpStatus.OK);
    }

   

    
}
