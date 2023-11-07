package kth.alex.demo.repository;

import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.MedicalCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalConditionRepository extends JpaRepository<MedicalCondition, Long> {
    @Query("select m from MedicalCondition m join fetch m.patient join fetch m.doctor where m.id = ?1")
    public Optional<MedicalCondition> findById(Long id);
}

