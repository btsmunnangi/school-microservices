package com.school.school;

import java.util.List;

public interface SchoolService {
 public List<SchoolDTO> findAllSchools();	
 public String saveSchool(School s);
 public String updateSchool(School school,Integer id);
 public String deleteSchool(Integer id);
 public SchoolDTO getSchoolById(Integer id);
 public void updateSchoolRating(ReviewMessage message);
 }
