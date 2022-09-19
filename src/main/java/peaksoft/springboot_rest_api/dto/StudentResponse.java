package peaksoft.springboot_rest_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private boolean isDeleted = false;
    private LocalDateTime created;
    private boolean Active = true;

}
