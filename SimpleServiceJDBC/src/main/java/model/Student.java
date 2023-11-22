package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {

    private Long id;

    private String name;

    private Integer age;

    private List<Course> courses = new ArrayList<>();

}
