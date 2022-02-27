package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Report;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import com.springproject.weathersharecommunity.repository.ReportRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    MemberRepository memberRepository;

    public Report boardReport(Report report){
        //Long id, Long boardId
//        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다."));

//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        Member member = (Member) user.getPrincipal();

        return reportRepository.save(report);
    }

    //게시물 신고 중복
    public boolean checkRepostService(Long boardId) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        List<Report> entity = reportRepository.findByTypeId(boardId);
        Long currentUserId = member.getId();

        if(entity == null)
            return true;

        for(int i = 0; i < entity.size(); i++){
            for(Report report : entity){
                Long reportId = entity.get(i).getReportUserId();
                if(currentUserId == reportId){
                    return false;
                }
            }
        }
        return true;
    }

    //댓글 신고
    public Report commentReport(Report report){
        return reportRepository.save(report);
    }

    //댓글 신고 중복
    public boolean checkCommentReportService(Long commentId){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        List<Report> entity = reportRepository.findByType("댓글");
        if(entity == null)
            return true;

        Long currentUserId = member.getId();

        for(int i = 0; i < entity.size(); i++){
            for(Report report : entity){
                if (entity.get(i).getTypeId() == commentId && entity.get(i).getReportUserId() == currentUserId)
                    return false;
            }
        }
        return true;
    }

}
