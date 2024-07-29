package com.project.studentapp.service;

import com.project.studentapp.entity.Student;
import com.project.studentapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
  private final StudentRepository studentRepository;


  // 이름, 성적 저장
  public Student addStudent(String name, int grade) {
    Student student = new Student(name, grade);
    studentRepository.add(student);

    return student;
  }

  public List<Student> getAll() {
    return studentRepository.getAll();
  }

  public List<Student> get(int grade) {
    return studentRepository.get(grade);
  }
}
