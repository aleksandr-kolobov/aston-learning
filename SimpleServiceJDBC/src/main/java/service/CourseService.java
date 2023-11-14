package service;

import repository.CoursRepository;

public class CourseService {

    private final CoursRepository repository;

    public CourseService(CoursRepository repository) {
        this.repository = repository;
    }
}