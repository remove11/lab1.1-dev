package kth.alex.demo.repository;

import kth.alex.demo.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
    @Query("select e from Observation e join fetch e.patient join fetch e.createdBy where e.id = ?1")
    public Optional<Observation> findById(String id);
}

