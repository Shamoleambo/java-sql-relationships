package com.tidz.relationships.dao;

import java.util.List;

import com.tidz.relationships.entity.Course;
import com.tidz.relationships.entity.Instructor;
import com.tidz.relationships.entity.InstructorDetail;
import com.tidz.relationships.entity.Student;

public interface AppDAO {

	void save(Instructor instructor);

	Instructor findInstructorById(int id);

	void deleteInstructorById(int id);

	InstructorDetail findInstructorDetailById(int id);

	void deleteInstructorDetailById(int id);

	List<Course> findCoursesByInstructorId(int id);

	Instructor findInstructorByIdJoinFetch(int id);

	void update(Instructor instructor);

	void update(Course course);

	Course findCourseById(int id);

	void deleteCourseById(int id);

	void save(Course course);
	
	Course findCourseAndReviews(int id);
	
	Course findCourseAndStudentsByCourseId(int id);
	
	Student findStudentAndCoursesByStudentId(int id);
	
	void update(Student student);
	
	void deleteStudentById(int id);

}
