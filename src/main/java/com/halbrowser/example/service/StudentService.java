package com.halbrowser.example.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.halbrowser.example.dao.StudentDAO;

@Service
public class StudentService {
	
	static Map<Integer,StudentDAO> mymap;
	
	private final static Logger logger=LoggerFactory.getLogger(StudentService.class);
	
	//StudentDAO dao=new StudentDAO(studentid, studentname, studentphone); 
	
	/*DATA*/
	public static Map<Integer, StudentDAO> StudentDetails() {
		
		logger.info("--studentDetails() -- {}");
		mymap=new ConcurrentHashMap<Integer, StudentDAO>();
		
		mymap.put(1, new StudentDAO(100, "JON", 111));
		mymap.put(2, new StudentDAO(200, "JAX", 222));
		mymap.put(3, new StudentDAO(300, "ANN", 333));
		mymap.put(4, new StudentDAO(400, "OM", 444));
		
		logger.info("--studentDetails() -- DATA {}"+mymap);
		return mymap;
		
	}
	/*Get the Student Details*/
	public static Map<Integer, StudentDAO> getStudentDetails() {
		
		logger.info("--studentDetails() --{}");
		Map<Integer, StudentDAO> studentData = StudentDetails();
		
		logger.info("--studentDetails() --RESPONSE {}"+studentData);
		return studentData;
		
		
	}
	/*Get the Student Details by ID*/
	public static List<Entry<Integer, StudentDAO>> createStudentDetailsbyId(int id) {
		
		logger.info("--studentDetailsbyId() --id {}"+id);
		
		Map<Integer, StudentDAO> studentData = StudentDetails();
		
			
			List<Entry<Integer, StudentDAO>> studentbyid = studentData.entrySet().stream().filter(x->x.getKey()==id)
					.collect(Collectors.toList());
			
			logger.info("--studentDetailsbyId() --RESPONSE {}"+studentbyid);			

			return studentbyid;
		
	}
	/*PUT the Student Details by ID*/
	public static Map<Integer, StudentDAO> createStudentDetails(int id, StudentDAO dao){
		
		logger.info("--createstudentDetails() --{}"+"id" + id + "DAO" + dao);
		Map<Integer, StudentDAO> studentData = StudentDetails();
		
		if(!studentData.containsKey(id)) {
			
			
			
			
			mymap.put(id, new StudentDAO(dao.getStudentid(),dao.getStudentname(),dao.getStudentphone()) );
			
		}else {
			
			logger.error("--createstudentDetails() --Key duplicate");
			
		}
		logger.info("--createstudentDetails() --RESPONSE{}" + mymap);
		
		return mymap;
		
		
	}
	/*POST the Student Details by ID*/
		public static Map<Integer, StudentDAO> updateStudentDetails(int id, StudentDAO dao) {
			
			logger.info("--updatestudentDetails() --id{}" + id +"DAO"+dao);
			Map<Integer, StudentDAO> studentData = StudentDetails();
			
			
			if(studentData.containsKey(id)) {
				
				mymap.put(id, new StudentDAO(dao.getStudentid(),dao.getStudentname(),dao.getStudentphone()) );
				
			}else {
				
				logger.error("--createStudentDetails() --Key not abalable");
				
			}
			logger.info("--updateStudentDetails() -- RESPONSE {}" + mymap);
			return mymap;
		}
		
		/*DELETE the Student Details by ID*/
	public static Map<Integer, StudentDAO> deleteStudentDetails(int id) {
		logger.info("--deleteStudentDetails() --id{}" + id);
		Map<Integer, StudentDAO> studentData = StudentDetails();
		
		if(studentData.containsKey(id)) {
			
			mymap.remove(id);
			
			
		}else {
			logger.error("--createStudentDetails() --Key not abalable");
		}
		logger.info("--deleteStudentDetails() --RESPONSE{}" +mymap);
        return mymap;
	}
}

