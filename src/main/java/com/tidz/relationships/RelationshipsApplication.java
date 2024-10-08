package com.tidz.relationships;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tidz.relationships.dao.AppDAO;
import com.tidz.relationships.entity.Course;
import com.tidz.relationships.entity.Instructor;
import com.tidz.relationships.entity.InstructorDetail;
import com.tidz.relationships.entity.Review;
import com.tidz.relationships.entity.Student;

@SpringBootApplication
public class RelationshipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLinerunner(AppDAO appDAO) {
		return runner -> {
//			createCourseAndStudents(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);
//			addMoreCoursesForStudent(appDAO);
			deleteStudent(appDAO);
		};
	}
	
	public void deleteStudent(AppDAO appDAO) {
		int id = 3;
		
		System.out.println("Delete student " + id);
		
		appDAO.deleteStudentById(id);
		System.out.println("Done");
	}
	
	public void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		
		Course course1 = new Course("How to make stuff");
		Course course2 = new Course("How to make a lot of stuff");
		
		student.add(course1);
		student.add(course2);
		
		appDAO.update(student);
		
		System.out.println("Saving student: " + student);
		System.out.println("Here courses: " + student.getCourses());
		
		System.out.println("Done");
	}

	public void findStudentAndCourses(AppDAO appDAO) {
		int id = 1;

		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		Course course = new Course("How to make super stuff");
		student.add(course);
		appDAO.update(student);

		System.out.println("Student " + student);
		System.out.println("Courses: " + student.getCourses());

		System.out.println("Done!");
	}

	public void findCourseAndStudents(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Loaded course: " + course);
		System.out.println("Students: " + course.getStudents());

		System.out.println("Done!");
	}

	public void createCourseAndStudents(AppDAO appDAO) {
		Course course1 = new Course("Cat petting");

		Student student1 = new Student("Mano", "Maneiro", "mano@mail.com");
		Student student2 = new Student("Truta", "Dahora", "truta@mail.com");
		Student student3 = new Student("Tiu", "Funil", "tiu@mail.com");

		course1.add(student1);
		course1.add(student2);
		course1.add(student3);

		System.out.println("Saving the MEGA course!");

		appDAO.save(course1);
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting the course " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("Find course " + id);

		Course course = appDAO.findCourseAndReviews(id);
		System.out.println("Here is the course " + course);
		System.out.println("Here are the reviews: " + course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("Le men 101");
		System.out.println("Saving the course: " + course);

		Review review1 = new Review("soo cool");
		Review review2 = new Review("lol, lmao even");
		Review review3 = new Review("first");

		course.add(review1);
		course.add(review2);
		course.add(review3);

		appDAO.save(course);
		System.out.println("Saved?? " + course.getReviews());
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 13;
		System.out.println("Deleting a super course " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done :(");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Where is my course " + id + "?");

		Course course = appDAO.findCourseById(id);
		System.out.println("Here it is: " + course);

		System.out.println("One update coming up");
		course.setTitle("MASTER BLASTER 101");
		appDAO.update(course);
		System.out.println("Done... not really");

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(1);
		System.out.println("Check the instructor now my friend: " + instructor);
		System.out.println("...finally: " + instructor.getCourses());
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("New update coming up");
		instructor.setEmail("superManoTheManForever7575@mail.com");

		appDAO.update(instructor);
		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("courses: " + instructor.getCourses());
		System.out.println("Done");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Findind Instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + instructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);

		System.out.println("The courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Findind Instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());

		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor1 = new Instructor("Mano", "Maneiro", "mano@mail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("@myMainGoal", "read");
		Course course1 = new Course("make fun advanced");
		Course course4 = new Course("make stuff");

		Instructor tempInstructor2 = new Instructor("Zinto", "Zaberino", "zz@mail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("@theStudent", "study");
		Course course2 = new Course("make fun 101");

		Instructor tempInstructor3 = new Instructor("Truta", "Dahora", "truta@mail.com");
		InstructorDetail instructorDetail3 = new InstructorDetail("@myMainGoal", "play videogames");
		Course course3 = new Course("do not make 101");

		tempInstructor1.setInstructorDetail(instructorDetail1);
		tempInstructor1.add(course1);
		tempInstructor1.add(course4);
		System.out.println("Saving instructor: " + tempInstructor1);
		appDAO.save(tempInstructor1);

		tempInstructor2.setInstructorDetail(instructorDetail2);
		tempInstructor2.add(course2);
		System.out.println("Saving instructor: " + tempInstructor2);
		appDAO.save(tempInstructor2);

		tempInstructor3.setInstructorDetail(instructorDetail3);
		tempInstructor3.add(course3);
		System.out.println("Saving instructor: " + tempInstructor3);
		appDAO.save(tempInstructor3);

		System.out.println("DONE!!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 5;
		System.out.println("Deleting the Instructor Detail with id: " + id);

		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 1;
		System.out.println("Find instructor Detail: " + id);

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("Instructor Detail: " + instructorDetail);
		System.out.println("Instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);

		System.out.println("Done!");

	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id " + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);
		System.out.println("instructor details: " + instructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor1 = new Instructor("Mano", "Maneiro", "mano@mail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("@myMainGoal", "read");
		Instructor tempInstructor2 = new Instructor("Zinto", "Zaberino", "zz@mail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("@theStudent", "study");
		Instructor tempInstructor = new Instructor("Truta", "Dahora", "truta@mail.com");
		InstructorDetail instructorDetail = new InstructorDetail("@myMainGoal", "play videogames");

		tempInstructor1.setInstructorDetail(instructorDetail1);
		System.out.println("Saving instructor: " + tempInstructor1);
		appDAO.save(tempInstructor1);
		tempInstructor.setInstructorDetail(instructorDetail);
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		tempInstructor2.setInstructorDetail(instructorDetail2);
		System.out.println("Saving instructor: " + tempInstructor2);
		appDAO.save(tempInstructor2);

		System.out.println("DONE!!!");

	}
}
