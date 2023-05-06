package com.learn_spring.learn_jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learn_spring.learn_jpa.dao.StudentDAO;
import com.learn_spring.learn_jpa.entity.Student;

@SpringBootApplication
public class LearnJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(@Qualifier("studentDao") StudentDAO studentDAO) {
		return runner -> {
			this.createMultipleStudents(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		int rowsDeleted = studentDAO.deleteAll();

		System.out.println("Rows Deleted: " + rowsDeleted);
	}

	private void removeStudent(StudentDAO studentDAO) {
		int studentId = 5;

		System.out.println("Deleting student id: " + studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//!SECTION - retrieve student based on the id: primary key

		int studentId = 5;

		System.out.println("Getting student with id: " + studentId);

		Student student = studentDAO.findById(studentId);

		//!SECTION - change the last name to Scooby

		student.setLastName("Kulsum");
		
		//!SECTION - update the student

		studentDAO.update(student);

		//!SECTION - display the updated student
		System.out.println("Update Student: " + student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//!SECTION - get a list of students
		var students = studentDAO.findByLastName("Doe");

		//!SECTION - display the list of students
		for (var student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//!SECTION - get list of all students

		var students = studentDAO.findAll();

		//!SECTION - display list of all students

		for (Student student : students) {
			System.out.println(student);
		}
		
	}

	private void readStudent(StudentDAO studentDAO) {

		//!SECTION - Create a student object
		System.out.println("Creating a new student object...");
		Student newStudent1 = new Student("Temp", "Student", "temp.student@email.com");

		//!SECTION - save the student
		System.out.println("Saving the student...");
		studentDAO.save(newStudent1);

		//!SECTION - display the id of the saved student
		int id = newStudent1.getId();
		System.out.println("Saved student. Generated id: " + id);

		//!SECTION - retrieve the student based on the id: primary key
		System.out.println("Retrieving student with id: " + id);
		Student retrievedStudent = studentDAO.findById(id);

		//!SECTION - Display student
		System.out.println("Found the student: " + retrievedStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//!SECTION - create multiple students

		System.out.println("Creating a new Student object...");

		Student newStudent1 = new Student("Thouhid", "Shaik", "shaik.thouhid@email.com");
		Student newStudent2 = new Student("John", "Doe", "john@email.com");
		Student newStudent3 = new Student("Mary", "Kom", "mary.kom@email.com");

		//!SECTION - save the students objects
		System.out.println("Saving the students...");
		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
		studentDAO.save(newStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		//!SECTION - Create the student object
		System.out.println("Creating a new Student object...");

		Student newStudent = new Student("Thouhid", "Shaik", "shaik.thouhid@email.com");

		//!SECTION - save the student object
		System.out.println("Saving the student...");
		studentDAO.save(newStudent);

		//!SECTION - Display the Id of the saved student
		System.out.println("Student saved. Generated Id: " + newStudent.getId());
		
	}

}
