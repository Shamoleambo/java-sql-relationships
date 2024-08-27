package com.tidz.relationships.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tidz.relationships.entity.Course;
import com.tidz.relationships.entity.Instructor;
import com.tidz.relationships.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;

	@Autowired
	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Instructor instructor) {
		this.entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		return this.entityManager.find(Instructor.class, id);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int id) {
		Instructor instructor = this.entityManager.find(Instructor.class, id);
		this.entityManager.remove(instructor);
	}

	@Override
	public InstructorDetail findInstructorDetailById(int id) {
		return this.entityManager.find(InstructorDetail.class, id);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int id) {
		InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, id);
		instructorDetail.getInstructor().setInstructorDetail(null);
		this.entityManager.remove(instructorDetail);
	}

	@Override
	public List<Course> findCoursesByInstructorId(int id) {
		TypedQuery<Course> query = this.entityManager.createQuery("from Course where instructor.id = :data",
				Course.class);
		query.setParameter("data", id);
		List<Course> courses = query.getResultList();

		return courses;
	}

}
