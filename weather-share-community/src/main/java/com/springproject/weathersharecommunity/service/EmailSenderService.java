//package com.springproject.weathersharecommunity.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class EmailSenderService {
//    private final JavaMailSender javaMailSender;
//
//    @Async
//    public void sendEmail(SimpleMailMessage email){
//        javaMailSender.send(email);
//    }
//}
