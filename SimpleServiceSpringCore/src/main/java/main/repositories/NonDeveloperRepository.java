package main.repositories;

import main.model.workers.NonDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonDeveloperRepository extends JpaRepository<NonDeveloper, Integer> {
}
