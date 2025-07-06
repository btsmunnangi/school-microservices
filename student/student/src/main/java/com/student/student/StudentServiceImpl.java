package com.student.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
 
public class StudentServiceImpl implements StudentService{

	final StudentRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	public StudentServiceImpl(StudentRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Student> findAllStudent() {
		logger.info("StudentServiceImpl::findAllStudent");
		return repo.findAll();
	}

	@Override
	public String saveStudent(Student s) {
		logger.info("StudentServiceImpl::saveStudent");
		if(repo.existsById(s.getId())) {
			throw new DuplicateRecordException("Student Record Already Exists:"+s.getId());
		}else {
			repo.save(s);
			return "Student Record Saved";
		}
	}
	@Override
    public String deleteStudent(Integer id) {
    	logger.info("StudentServiceImpl::deleteStudent");
    	if(repo.existsById(id)) {
    	  repo.deleteById(id);
    	  return "Record Deleted";
    	}else {
    		throw new ResourceNotFoundException("Student Not Found With Id:"+id);
    	}
    }
	@Override
    public String updateStudent(Student st,Integer id) {
    	logger.info("StudentServiceImpl::updateStudent");
    	if(repo.existsById(id)) {
    		Student std = repo.findById(id).get();
    		std.setFirstName(st.getFirstName());
    		std.setLastName(st.getLastName());
    		std.setEmail(st.getEmail());
    	    repo.save(std);
    	    return "Record Updated";
    	}else {
    		throw new ResourceNotFoundException("Student Not Found With Id:"+id);
    	}
    }
	@Override
    public Student getStudent(Integer id) {
    	logger.info("StudentServiceImpl::getStudent:"+id);
    	return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not Found With Id:"+id));
    }
}
