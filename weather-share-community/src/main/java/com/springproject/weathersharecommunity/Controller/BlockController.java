package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BlockDto;
import com.springproject.weathersharecommunity.domain.Block;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping("/block/create")
    public Block block(BlockDto blockDto){
        Block block = new Block();

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        block.setMember(member);
        block.setBlockedMember(blockDto.getBlockedMember());

        blockService.block(block);

        return block;
    }

    //내가 차단한 유저 확인
    @GetMapping("/block")
    public List<Block> blockList(Member member){
        List<Block> blockList = blockService.findByMember(member);
        return blockList;
    }

    //차단 풀기
    @DeleteMapping("/block/delete")
    public void deleteBlock(BlockDto blockDto){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        Optional<Block> block = blockService.findById(blockDto.getBlockId());
    }


}
