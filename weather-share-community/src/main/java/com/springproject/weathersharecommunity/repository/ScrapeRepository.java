package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Scrape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScrapeRepository extends JpaRepository<Scrape, Long> {

    Optional<Scrape> findByBoardAndMember(Board board, Member member);

    public List<Scrape> findAllByMember(Member member);
}
