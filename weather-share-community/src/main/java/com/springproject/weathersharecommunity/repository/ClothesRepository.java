package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.clothes.Clothes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.login.AccountLockedException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClothesRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Clothes clothes){
        em.persist(clothes);
    }

    public Clothes findOne(Long id){
        return em.find(Clothes.class, id);
    }

    public List<Clothes> findAll(){
        return em.createQuery("select c from Clothes c", Clothes.class)
                .getResultList();
    }

    public List<Clothes> findByBoard(Long board){
        return em.createQuery("select c from Clothes c where c.board = :board", Clothes.class)
                .setParameter("board", board)
                .getResultList();
    }
}
