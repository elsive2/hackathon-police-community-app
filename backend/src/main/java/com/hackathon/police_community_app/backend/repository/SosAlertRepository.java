package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.SosAlert;
import com.hackathon.police_community_app.backend.exception.SosAlertNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SosAlertRepository extends CrudRepository<SosAlert, Long> {
    Page<SosAlert> findByIsDeletedFalseOrderByCreateDateDesc(Pageable pageable);

    default Page<SosAlert> findAll(Pageable pageable) {
        return findByIsDeletedFalseOrderByCreateDateDesc(pageable);
    }

    Page<SosAlert> findByPhoneNumberAndIsDeletedFalseOrderByCreateDateDesc(String phoneNumber, Pageable pageable);

    default Page<SosAlert> findByPhone(String phoneNumber, Pageable pageable) {
        return findByPhoneNumberAndIsDeletedFalseOrderByCreateDateDesc(phoneNumber, pageable);
    }

    Optional<SosAlert> findByIdAndIsDeletedFalse(Long id);

    default SosAlert findByIdRequired(Long id) {
        return findByIdAndIsDeletedFalse(id).orElseThrow(SosAlertNotFoundException::new);
    }
}
