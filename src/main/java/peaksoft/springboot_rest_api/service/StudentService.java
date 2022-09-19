package peaksoft.springboot_rest_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.springboot_rest_api.dto.StudentRequest;
import peaksoft.springboot_rest_api.dto.StudentResponse;
import peaksoft.springboot_rest_api.dto.StudentResponseView;
import peaksoft.springboot_rest_api.entity.Student;
import peaksoft.springboot_rest_api.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

//
//    public Student addStudent(Student student) {
//       return repository.save(student);
//    }

    public StudentResponse addStudent2(StudentRequest request) {
        Student student = createStudent(request);
        return getStudentResponse(studentRepository.save(student));
    }

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id).get();
        Student student1 = updateStudent(student, request);
        studentRepository.save(student1);
        return getStudentResponse(student1);
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository.findById(id).get();
        return getStudentResponse(student);
    }


    public Student updateStudent(Student student, StudentRequest request) {
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        if (student.getName() == null) {
            student.setName("This filed not filled NULL");
        }
        student.setAge(request.getAge());
        if (student.getAge() == 0) {
            student.setAge(99);
        }
        student.setEmail(request.getEmail());
        if (student.getEmail() == null) {
            student.setEmail("Это поле не заполнено NULL");
        }
        student.setDeleted(student.isDeleted());
        student.setActive(student.isActive());
        student.setCreated(LocalDateTime.now());
        return student;
    }


    public StudentResponse deleteById(Long id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return getStudentResponse(student);
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }


    public StudentResponseView getAllStudentsPagination(String text, int page, int size) {
        StudentResponseView studentResponseView = new StudentResponseView();
        Pageable pageable = PageRequest.of(page - 1, size);
        studentResponseView.setStudentResponse(getStudents(search(text, pageable)));
        return studentResponseView;
    }


    public List<StudentResponse> getStudents(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(getStudentResponse(student));
        }
        return responses;
    }

    private List<Student> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return studentRepository.searchByName(text.toUpperCase(), pageable);
    }


    public StudentResponse block(Long id) {
        Student student = studentRepository.findById(id).get();
        student.setActive(false);
        studentRepository.save(student);
        return getStudentResponse(student);

    }


    public Student createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setDeleted(student.isDeleted());
        student.setActive(student.isActive());
        student.setCreated(LocalDateTime.now());
        return student;
    }

    public StudentResponse getStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setSurname(student.getSurname());
        studentResponse.setAge(student.getAge());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setActive(student.isActive());
        studentResponse.setCreated(student.getCreated());
        studentResponse.setDeleted(student.isDeleted());
        return studentResponse;
    }
}
