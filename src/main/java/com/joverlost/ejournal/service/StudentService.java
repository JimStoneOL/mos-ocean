package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.StudentDTO;
import com.joverlost.ejournal.entity.Student;
import com.joverlost.ejournal.exception.StudentNotFoundException;
import com.joverlost.ejournal.facade.StudentFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentFacade studentFacade) {
        this.studentRepository = studentRepository;
        this.studentFacade = studentFacade;
    }

    public Student createStudent(StudentDTO studentDTO){
        Student student=studentFacade.studentDtoToStudent(studentDTO);
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        return student;
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public MessageResponse deleteStudentById(Long id){
        studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        studentRepository.deleteById(id);
        return new MessageResponse("Студент успешно удалён");
    }

    public Student updateStudent(StudentDTO studentDTO){
        studentRepository.findById(studentDTO.getId()).orElseThrow(()->new StudentNotFoundException("Студент не найден"));
        return studentRepository.save(studentFacade.studentDtoToStudent(studentDTO));
    }
}
