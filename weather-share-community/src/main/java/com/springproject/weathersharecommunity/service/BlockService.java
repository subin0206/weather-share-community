package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.domain.Block;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlockService {
    @Autowired
    BlockRepository blockRepository;

    /*차단 저장*/
    public Block block(Block block){
        return blockRepository.save(block);
    }

    /*차단 풀기*/
//    public void delete(Optional<Block> block){
//        blockRepository.delete(block);
//    }


    public List<Block> findByMember(Member member) {
        return blockRepository.findByMember(member);
    }

    public Optional<Block> findById(long blockId) {

        return blockRepository.findById(blockId);
    }
}
