package main.model.workers;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("Developer")
public class Developer extends Worker {

    @Column(name = "programming_language")
    private String programmingLanguage;
}


