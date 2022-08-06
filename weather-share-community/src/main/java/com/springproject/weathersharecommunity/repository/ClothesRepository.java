package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Clothes findAllByBoardId(Long boardId);
}
