package com.hackathon.police_community_app.backend.repository;

import com.hackathon.police_community_app.backend.entity.SmsCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SmsCodeRepository extends CrudRepository<SmsCode, Long> {
    Optional<SmsCode> findByPhoneNumberAndCode(String phoneNumber, String code);

    default Optional<SmsCode> findByPhoneNumberAndCodeOptional(String phoneNumber, String code) {
        return findByPhoneNumberAndCode(phoneNumber, code);
    }

}
