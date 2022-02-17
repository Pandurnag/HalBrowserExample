package com.halbrowser.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.halbrowser.example.dao.StudentDAO;
import com.halbrowser.example.service.StudentService;
import com.halbrowser.example.to.Student;

/*
 * localhost
 */

@RestController
public class StudentController {
	
	private final static Logger logger=LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService service;
	

	@GetMapping("/studentdetails")
	public Map<Integer, StudentDAO> getMyStudentDeatails() {
	 logger.info("--getMyStudentDeatails() -- {}");	
     return service.getStudentDetails();
		
	}

	@GetMapping("/studentdetailsbyid/{id}")
	public List<Entry<Integer, StudentDAO>> getMyStudentDeatailsbyId(@PathVariable int id) {
		logger.info("--getMyStudentDeatailsbyId() -- {}");
		return service.createStudentDetailsbyId(id);
		
	}

	@PostMapping("/updatestudentdetailsbyid/{id}")
	public Map<Integer, StudentDAO> updatetMyStudentDeatailsbyId(@PathVariable int id ,@RequestBody Student to) {
		
		StudentDAO dao=new StudentDAO();
		dao.setStudentid(to.getStudentid());
		dao.setStudentname(to.getStudentname());
		dao.setStudentphone(to.getStudentphone());
		
		Map<Integer, StudentDAO> updatestudentdetails = service.updateStudentDetails(id, dao);
		logger.info("--updatetMyStudentDeatailsbyId() -- {}"+updatestudentdetails);
		return updatestudentdetails;
		
    }
	
	@PutMapping("/createstudentdetailsbyid/{id}")
    public Map<Integer, StudentDAO> createMyStudentDetailsbyId(@PathVariable int id ,@RequestBody Student to) {
		
		StudentDAO dao=new StudentDAO();
		dao.setStudentid(to.getStudentid());
		dao.setStudentname(to.getStudentname());
		dao.setStudentphone(to.getStudentphone());
		
		Map<Integer, StudentDAO> newstudentdata = service.createStudentDetails(id, dao);
		logger.info("--createMyStudentDetailsbyId() -- {}"+ newstudentdata);
		return newstudentdata;
    	
}   
    @DeleteMapping("/deletestudentdetailsbyid/{id}")
	public Map<Integer, StudentDAO> deleteMyStudentDetailsbyId(@PathVariable int id) {
    	
    	Map<Integer, StudentDAO> afterdeleting = service.deleteStudentDetails(id);
    	logger.info("--deleteMyStudentDetailsbyId() -- {}"+ afterdeleting);
        return afterdeleting;
    	
    }
}

