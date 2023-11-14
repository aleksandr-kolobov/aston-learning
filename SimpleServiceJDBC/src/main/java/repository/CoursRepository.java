package repository;

import model.Cours;
import java.util.List;

public interface CoursRepository {

    Cours save(Cours meal);

    boolean delete(int id);

    Cours get(int id);

    List<Cours> getAll();

}
