package com.student.student;

import java.util.List;

public interface StudentService {
	List<Student> findAllStudent();
	String saveStudent(Student s);
	String updateStudent(Student s,Integer id);
	String deleteStudent(Integer id);
	Student getStudent(Integer id);
}
