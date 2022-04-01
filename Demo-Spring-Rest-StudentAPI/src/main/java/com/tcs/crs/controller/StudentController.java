package com.tcs.crs.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.tcs.dao.StudentDao;
import com.tcs.model.CourseCatalog;
import com.tcs.model.Student;




/**
 * @author springuser18
 *
 */
@RestController
public class StudentController {
	
	@Autowired //The meaning of @Autowired is put the dependency injection at the Dao layer
	private StudentDao crsDAO;
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
		    method = RequestMethod.GET,
		    value = "/registration")
		@ResponseBody
		public List getStudent() {
		

		
		return crsDAO.list();
	}
	

	//Using this method get details by student by id
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
		    method = RequestMethod.GET,
		    value = "/registration/{id}")
		@ResponseBody
	public ResponseEntity getStudent(@PathVariable("id") Long id) {

		Student student = crsDAO.get(id);
		if (student == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(student, HttpStatus.OK);
	}
	
	//Using This Api to Post student data
		@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			    method = RequestMethod.POST,
			    value = "/post/registration")
			@ResponseBody	
			public ResponseEntity createStudent(@RequestBody Student student) {

			crsDAO.create(student);

			return new ResponseEntity(student, HttpStatus.OK);
		}
		
		//This method is using to delete the student
		@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			    method = RequestMethod.DELETE,
			    value = "/delete/registration/{id}")
			@ResponseBody	
		public ResponseEntity deleteStudent(@PathVariable Long id) {

			if (null == crsDAO.delete(id)) {
				return new ResponseEntity("No student found for ID " + id, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity(id, HttpStatus.OK);

		}
		
		//This method is using to update the student
		@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			    method = RequestMethod.PUT,
			    value = "/put/registration/{id}")
			@ResponseBody	
		public ResponseEntity updateStudent(@PathVariable Long id, @RequestBody Student student) {

			student = crsDAO.update(id, student);

			if (null == student) {
				return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity(student, HttpStatus.OK);
		}}
		