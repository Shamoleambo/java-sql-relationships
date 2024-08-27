package com.tidz.relationships.dao;

import com.tidz.relationships.entity.Instructor;
import com.tidz.relationships.entity.InstructorDetail;

public interface AppDAO {

	void save(Instructor instructor);

	Instructor findInstructorById(int id);

	void deleteInstructorById(int id);

	InstructorDetail findInstructorDetailById(int id);
}
