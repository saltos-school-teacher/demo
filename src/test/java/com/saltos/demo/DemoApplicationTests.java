package com.saltos.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	StudentRepository studentRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void insertStudent() {
		StudentData student = new StudentData(null, "1234", 25);
		StudentData savedStudent = studentRepository.save(student);
		Integer savedStudentId = savedStudent.getStudentId();
		Assertions.assertNotNull(savedStudentId, "The ID saved should not be null");
		Optional<StudentData> foundStudentOptional = studentRepository.findById(savedStudentId);
		Assertions.assertTrue(foundStudentOptional.isPresent(),
				"Unable to find saved student with ID " + savedStudentId);
		StudentData foundStudent = foundStudentOptional.get();
		Assertions.assertEquals(savedStudent, foundStudent,
				"The saved student is not the same as the one found at the database");
 	}

 	@Test
	void deleteStudent() {
		StudentData student = new StudentData(null, "12345", 35);
		StudentData savedStudent = studentRepository.save(student);
		Integer savedStudentId = savedStudent.getStudentId();
		Assertions.assertNotNull(savedStudentId, "The ID saved should not be null");
		Optional<StudentData> studentDataOptional = studentRepository.findById(savedStudentId);
		Assertions.assertTrue(studentDataOptional.isPresent());
		studentRepository.deleteById(savedStudentId);
		Optional<StudentData> studentDataDeleted = studentRepository.findById(savedStudentId);
		Assertions.assertFalse(studentDataDeleted.isPresent());
	}

	@Test
	void updateStudent() {
		StudentData student = new StudentData(null, "123456", 39);
		StudentData savedStudent = studentRepository.save(student);
		Integer savedStudentId = savedStudent.getStudentId();
		Assertions.assertNotNull(savedStudentId, "The ID saved should not be null");
		// Unavailable to change the PK
		// savedStudent.setStudentId(50);
		StudentData changedStudent = savedStudent;
		changedStudent.setFullname("new fullname");
		changedStudent.setAge(40);
		studentRepository.save(changedStudent);
		Optional<StudentData> foundUpdatedStudentOptional = studentRepository.findById(savedStudentId);
		Assertions.assertTrue(foundUpdatedStudentOptional.isPresent());
		StudentData foundUpdatedStudent = foundUpdatedStudentOptional.get();
		Assertions.assertEquals(changedStudent, foundUpdatedStudent);
	}

}
