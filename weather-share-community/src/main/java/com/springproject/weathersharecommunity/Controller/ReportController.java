package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardReportDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Report;
import com.springproject.weathersharecommunity.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report/board") //id=글쓴이 id
    public Report boardReport(BoardReportDto boardReportDto){
        Report report = new Report();
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        boolean checkOfReport = reportService.checkRepostService(boardReportDto.getTypeId());

        return report;
    }


}
