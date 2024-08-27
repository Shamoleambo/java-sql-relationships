package com.tidz.relationships.dao;

import com.tidz.relationships.entity.Instructor;

public interface AppDAO {

	void save(Instructor instructor);

	Instructor findInstructorById(int id);
	
	void deleteInstructorById(int id);
}
