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
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
//		Instructor tempInstructor = new Instructor("Mano", "Maneiro", "mano@mail.com");
//		InstructorDetail instructorDetail = new InstructorDetail("@myMainGoal", "read");
		Instructor tempInstructor = new Instructor("Truta", "Dahora", "truta@mail.com");
		InstructorDetail instructorDetail = new InstructorDetail("@myMainGoal", "play videogames");

		tempInstructor.setInstructorDetail(instructorDetail);
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("DONE!!!");

	}
}
