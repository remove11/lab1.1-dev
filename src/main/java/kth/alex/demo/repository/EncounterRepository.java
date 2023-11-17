package kth.alex.demo.repository;

import kth.alex.demo.entity.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter, String> {
    @Query("select e from Encounter e join fetch e.patient join fetch e.createdBy where e.id = ?1")
    public Optional<Encounter> findById(String id);
    public List<Encounter> findListById(String id);
    @Query("select e from Encounter e join fetch e.patient join fetch e.createdBy where e.patient.socialNr = ?1")
    public List<Encounter> findByPersonNr(String personNr);
}
