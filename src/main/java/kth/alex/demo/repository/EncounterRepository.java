package kth.alex.demo.repository;

import kth.alex.demo.entity.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {
}

