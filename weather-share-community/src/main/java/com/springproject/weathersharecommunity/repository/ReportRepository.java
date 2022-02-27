package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByTypeId(Long id);

    List<Report> findByType(String type);
}
