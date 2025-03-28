package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.SmsCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsCodeRepository extends CrudRepository<SmsCode, Long> {
    Optional<SmsCode> findFirstByPhoneNumberAndCodeOrderByCreateDateDesc(String phoneNumber, String code);

    default Optional<SmsCode> findByPhoneNumberAndCodeOptional(String phoneNumber, String code) {
        return findFirstByPhoneNumberAndCodeOrderByCreateDateDesc(phoneNumber, code);
    }

}
