package com.zerobase.luffy.member.bm.repository;

import com.zerobase.luffy.member.bm.entity.BrandManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ManagerRepository {

    @PersistenceContext
    EntityManager em;


    public void save(BrandManager bm){

        em.persist(bm);
    }

    public BrandManager findOne(Long id){
        return em.find(BrandManager.class,id);

    }
    public List<BrandManager> findAll(){
        return em.createQuery("select b from BrandManager b",BrandManager.class)
                .getResultList();
    }
    public List<BrandManager> findByName(String managerName){
        return em.createQuery("select  b from BrandManager b where b.managerName =:managerName",BrandManager.class)
                .setParameter("managerName",managerName)
                .getResultList();
    }


}
