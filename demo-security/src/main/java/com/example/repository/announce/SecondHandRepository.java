package com.example.repository.announce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.entity.announce.Announce;
import com.example.entity.announce.SecondHand;


public interface SecondHandRepository extends JpaRepository<SecondHand, Long>, JpaSpecificationExecutor<SecondHand>, PagingAndSortingRepository<SecondHand, Long>{
    
}
