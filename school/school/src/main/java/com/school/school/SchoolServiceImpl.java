package com.school.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final MessageConverter jsonMessageConverter;
    final SchoolRepository repo;
    StudentClient client;
    int attempt =0;
  //  @Autowired
  //  RestTemplate restTemplate;
    private final static Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);
    
	public SchoolServiceImpl(SchoolRepository repo,StudentClient client, MessageConverter jsonMessageConverter) {
		this.repo = repo;
		this.client = client;
		this.jsonMessageConverter = jsonMessageConverter;
	}

	@Override
	@CircuitBreaker(name="schoolBreaker" ,fallbackMethod = "schoolBreakerFallBack")
	@Retry(name="schoolRetry" ,fallbackMethod = "schoolRetryFallBack")
	@RateLimiter(name="schoolBreaker", fallbackMethod = "schoolRateFallBack" )
	public List<SchoolDTO> findAllSchools() {
		logger.info("SchoolServiceImpl::findAllSchools:Attempt"+(++attempt));
		logger.info("SchoolServiceImpl::findAllSchools");
		List<School> list = repo.findAll();
		return list.stream().map(this::convertToSchoolDTO).collect(Collectors.toList());
	}
    public List<String> schoolBreakerFallBack(Exception ecx){
    	List<String> l = new ArrayList<String>();
    	l.add("Circuit had broken:");
    	return l;
    }
    public List<String> schoolRetryFallBack(Exception ex) {
    	List<String> error = new ArrayList<String>();
    	error.add("Fall back method for retry");
    	return error;
    }
    public List<String> schoolRateFallBack(Exception e){
    	List<String> limit = new ArrayList<String>();
    	limit.add("Rate Limit Crossed");
    	return limit;
    }
	@Override
	public String saveSchool(School s) {
		logger.info("SchoolServiceImpl::saveSchool");
		if(repo.existsById(s.getId())) {
			throw new DuplicateRecordException("School Record Already Exists:"+s.getId());
		}else {
		repo.save(s);
		return "Record Saved";
		}
	}

	@Override
	public String updateSchool(School school, Integer id) {
		logger.info("SchoolServiceImpl::updateSchool");
		if(repo.existsById(id)) {
			School sch =repo.findById(id).get();
			sch.setEmail(school.getEmail());
			sch.setName(school.getName());
			repo.save(sch);
			return "Records Updated";
		}else {
			throw new ResourceNotFoundException("School Not Found With ID:"+id);
		}
	}

	@Override
	public String deleteSchool(Integer id) {
		logger.info("SchoolServiceImpl::deleteSchool");
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return "Record Deleted";
		}else {
			throw new ResourceNotFoundException("School Not Found With ID:"+id);
		}
		
	}
	@Override
	public SchoolDTO getSchoolById(Integer id) {
		School school = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("School Not Found With ID:"+id));
		return convertToSchoolDTO(school); 
				
	}
	private SchoolDTO convertToSchoolDTO(School sc1) {
		logger.error("convertToSchoolDTO:"+sc1.getStudentId());
		//Student st = restTemplate.getForObject("http://student-service/api/v1/students/1", Student.class);
		if(sc1.getStudentId() ==null) {
			throw new IllegalArgumentException("Student ID cannot be null for school ID: "+sc1.getStudentId());
		}
		Student st = client.getStudent(sc1.getStudentId());
		SchoolDTO dto = new SchoolDTO();
		logger.info(st.getFirstName());
		dto.setId(sc1.getId());
		dto.setEmail(sc1.getEmail());
		dto.setName(sc1.getName());
		dto.setStudent(st);
		return dto;
	}

	@Override
	public void updateSchoolRating(ReviewMessage message) {
		logger.info("SchoolServiceImpl:updateSchoolRating:"+message.getSchoolId()+":"+message.getRating());
		Optional<School> sc = repo.findById(message.getSchoolId());
		if(sc.isPresent()) {
			School s1 = sc.get();
			Integer count =s1.getReviewCount();
			s1.setReviewCount(count++);
		}
	}

}
