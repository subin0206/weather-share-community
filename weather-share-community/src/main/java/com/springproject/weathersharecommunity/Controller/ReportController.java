package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardReportDto;
import com.springproject.weathersharecommunity.Controller.dto.CommentReportDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Report;
import com.springproject.weathersharecommunity.service.MemberService;
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
    @Autowired
    MemberService memberService;

    /*
    게시물 신고
     */
    @PostMapping("/report/board") //id=글쓴이 id
    public Report boardReport(BoardReportDto boardReportDto){
        Report report = new Report();
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        report.setReportUserId(member.getId());
        report.setReportedUserId(memberService.findOne(boardReportDto.getReportedUserId()));
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
    @PostMapping("/report/comment")
    public Report commentReport(CommentReportDto commentReportDto){
        Report report = new Report();
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        report.setReportUserId(member.getId());
        report.setReportedUserId(memberService.findOne(commentReportDto.getReportedUserId()));
        report.setType("게시판");
        report.setTypeId(commentReportDto.getTypeId());

        boolean checkOfComment = reportService.checkCommentReportService(commentReportDto.getTypeId());

        if(checkOfComment)
            reportService.commentReport(report);

        return report;
    }

}
