package com.tidz.relationships;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tidz.relationships.dao.AppDAO;
import com.tidz.relationships.entity.Instructor;
import com.tidz.relationships.entity.InstructorDetail;

@SpringBootApplication
public class RelationshipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLinerunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
		};
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
