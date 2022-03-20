package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.domain.ConfirmToken;
import com.springproject.weathersharecommunity.repository.ConfirmTokenRepository;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfirmTokenService {
    private final ConfirmTokenRepository confirmTokenRepository;
    private final EmailSenderService emailSenderService;
    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    public String createEmailConfirmToken(String userId, String receiverEmail) {
        Assert.hasText(userId, "userId는 필수입니다.");
        Assert.hasText(receiverEmail, "receiverEmail은 필수입니다.");
        ConfirmToken confirmToken = ConfirmToken.createEmailConfirmToken(userId);
        confirmTokenRepository.save(confirmToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
//        mailMessage.setText("http:localhost:8070/confirm-email?token="+confirmToken.getId());
        mailMessage.setText("https:52.78.183.55:8080/confirm-email?token="+confirmToken.getId());
        emailSenderService.sendEmail(mailMessage);
        return confirmToken.getId();
    }
    /**
     * 유효한 토큰 가져오기
     * @param confirmTokenId
     * @return
     */
    public ConfirmToken findByIdAndExpired(String confirmTokenId) {
        Optional<ConfirmToken> confirmToken = confirmTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmTokenId, LocalDateTime.now(), false);
        return confirmToken.orElseThrow(()->new IllegalArgumentException("토큰을 찾을 수 없습니다."));
    }

}
