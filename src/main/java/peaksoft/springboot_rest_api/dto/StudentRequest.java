package peaksoft.springboot_rest_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    private String name;
    private String surname;
    private int age;
    private String email;

}
