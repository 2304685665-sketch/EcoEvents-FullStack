package com.ecoevents.ecoevents.repository;
import com.ecoevents.ecoevents.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long>{
}
