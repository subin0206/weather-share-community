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

    /*
    게시물 신고
     */
    @PostMapping("/report/board") //id=글쓴이 id
    public Report boardReport(BoardReportDto boardReportDto){
        Report report = new Report();
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        report.setReportUserId(member.getId());
        report.setReportedUserId(boardReportDto.getReportedUserId());
        report.setType("게시판");
        report.setTypeId(boardReportDto.getTypeId());

        boolean checkOfReport = reportService.checkRepostService(boardReportDto.getTypeId());

        if(checkOfReport){ //신고된 게시물이 없다면
            reportService.boardReport(report);
        }

        return report;
    }

    /*
    댓글 신고
     */

    public Report commentReport(){
        Report report = new Report();
        return report;
    }

}
