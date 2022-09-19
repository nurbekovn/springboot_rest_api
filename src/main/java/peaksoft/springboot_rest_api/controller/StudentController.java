package peaksoft.springboot_rest_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot_rest_api.dto.StudentRequest;
import peaksoft.springboot_rest_api.dto.StudentResponse;
import peaksoft.springboot_rest_api.dto.StudentResponseView;
import peaksoft.springboot_rest_api.entity.Student;
import peaksoft.springboot_rest_api.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

//    @PostMapping
//    public Student create(@RequestBody Student student) {
//        return studentService.addStudent(student);
//    }


//    @PostMapping
//    public Student create(@RequestBody StudentRequest studentRequest) {
//        return studentService.addStudent2(studentRequest);
//    }


    @PostMapping
    public StudentResponse create (@RequestBody StudentRequest studentRequest) {
        return studentService.addStudent2(studentRequest);

    }


    @GetMapping("{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }


    @DeleteMapping("{id}")
    public StudentResponse deleteById(@PathVariable Long id) {
        return studentService.deleteById(id);
    }


    @PatchMapping("{id}")
    public StudentResponse updateStudent(@PathVariable Long id,
                                         @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id,studentRequest);

    }

    @GetMapping("/all")
    public List<Student> getAllStudent() {
        return studentService.findAllStudent();
    }

    @PutMapping("block/{id}")
    public StudentResponse blockStudent(@PathVariable Long id) {
        return studentService.block(id);

    }


    @GetMapping
    public StudentResponseView getAllPagination(@RequestParam(name = "text", required = false)
                                                String text,
                                                @RequestParam int page,
                                                @RequestParam int size){
        return studentService.getAllStudentsPagination(text,page,size);
    }
}
