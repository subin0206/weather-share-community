package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
