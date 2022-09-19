package peaksoft.springboot_rest_api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen",sequenceName = "student_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private boolean isDeleted = false;
    private boolean Active = true;
    @CreatedDate
    private LocalDateTime created;

}
