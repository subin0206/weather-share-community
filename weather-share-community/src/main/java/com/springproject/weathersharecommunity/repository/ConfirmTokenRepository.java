package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.ConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;

public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, String> {
    Optional<ConfirmToken> findByIdAndExpirationDateAfterAndExpired(String confirmTokenId, LocalDateTime now, boolean expired);
}
