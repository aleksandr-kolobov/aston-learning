package main.model.workers;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("NonDeveloper")
public class NonDeveloper extends Worker {

    @Column(name = "role")
    private String role;
}
