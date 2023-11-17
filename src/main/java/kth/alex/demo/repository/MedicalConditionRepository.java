package kth.alex.demo.repository;

import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.MedicalCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalConditionRepository extends JpaRepository<MedicalCondition, Long> {
    @Query("select m from MedicalCondition m join fetch m.patient join fetch m.doctor where m.id = ?1")
    public Optional<MedicalCondition> findById(String id);

    @Query("select m from MedicalCondition m join fetch m.patient join  fetch m.doctor where m.patient.socialNr = ?1")
    public List<MedicalCondition> findByPatientSocialNr(String patientId);
}

