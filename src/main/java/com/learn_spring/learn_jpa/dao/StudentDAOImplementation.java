package com.learn_spring.learn_jpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learn_spring.learn_jpa.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository("studentDao")
public class StudentDAOImplementation implements StudentDAO {

	//!SECTION - Define fields for entity manager
	private EntityManager entityManager;


	//!SECTION - Inject entity manager using constructor injection

	public StudentDAOImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	//!SECTION - Implement save method.
	
	@Override
	@Transactional
	public void save(Student student) {
		this.entityManager.persist(student);
	}
	
	@Override
	public Student findById(int id) {
		return this.entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> findAll() {
		TypedQuery<Student> query = this.entityManager.createQuery("FROM Student order by lastName asc", Student.class);

		return query.getResultList();
	}
	
	@Override
	public List<Student> findByLastName(String lastName) {
		TypedQuery<Student> query = this.entityManager.createQuery("FROM Student WHERE lastName=:data", Student.class);

		query.setParameter("data", lastName);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void update(Student student) {
		this.entityManager.merge(student);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);
	}
	
	@Override
	@Transactional
	public int deleteAll() {
		int numberOfRowsDeleted = this.entityManager.createQuery("DELETE FROM Student").executeUpdate();

		return numberOfRowsDeleted;
	}
}
