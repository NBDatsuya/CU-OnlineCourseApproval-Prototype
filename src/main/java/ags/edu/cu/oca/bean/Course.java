package ags.edu.cu.oca.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String uuid;
    private String courseName;
    private String instructor;
    private String schedule;
}