package com.tidz.relationships.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tidz.relationships.entity.Course;
import com.tidz.relationships.entity.Instructor;
import com.tidz.relationships.entity.InstructorDetail;
import com.tidz.relationships.entity.Student;

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

		List<Course> courses = instructor.getCourses();
		for (Course course : courses) {
			course.setInstructor(null);
		}

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

	@Override
	public Instructor findInstructorByIdJoinFetch(int id) {
		TypedQuery<Instructor> query = this.entityManager.createQuery("select i from Instructor i "
				+ "JOIN FETCH i.courses " + "JOIN FETCH i.instructorDetail " + "where i.id = :data", Instructor.class);
		query.setParameter("data", id);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public void update(Instructor instructor) {
		this.entityManager.merge(instructor);
	}

	@Override
	@Transactional
	public void update(Course course) {
		this.entityManager.merge(course);
	}

	@Override
	public Course findCourseById(int id) {
		return this.entityManager.find(Course.class, id);
	}

	@Override
	@Transactional
	public void deleteCourseById(int id) {
		Course course = this.entityManager.find(Course.class, id);
		this.entityManager.remove(course);
	}

	@Override
	@Transactional
	public void save(Course course) {
		this.entityManager.persist(course);
	}

	@Override
	public Course findCourseAndReviews(int id) {
		TypedQuery<Course> query = this.entityManager
				.createQuery("select c from Course c " + "JOIN FETCH c.reviews " + "where c.id = :data", Course.class);
		query.setParameter("data", id);
		return query.getSingleResult();
	}

	@Override
	public Course findCourseAndStudentsByCourseId(int id) {
		TypedQuery<Course> query = this.entityManager
				.createQuery("select c FROM Course c " + "JOIN FETCH c.students " + "WHERE c.id = :data", Course.class);
		query.setParameter("data", id);
		return query.getSingleResult();
	}

	@Override
	public Student findStudentAndCoursesByStudentId(int id) {
		TypedQuery<Student> query = this.entityManager.createQuery(
				"SELECT s FROM Student s " + "JOIN FETCH s.courses " + "WHERE s.id = :data", Student.class);
		query.setParameter("data", id);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public void update(Student student) {
		this.entityManager.merge(student);
	}

	@Override
	@Transactional
	public void deleteStudentById(int id) {
		Student student = this.entityManager.find(Student.class, id);
		this.entityManager.remove(student);
	}
}
