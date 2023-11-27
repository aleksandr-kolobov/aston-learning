package main.model.abstractModel;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractWorkerFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
