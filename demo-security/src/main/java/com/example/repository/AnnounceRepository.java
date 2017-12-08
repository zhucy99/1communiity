package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.entity.Announce;


public interface AnnounceRepository extends JpaRepository<Announce, Long>, JpaSpecificationExecutor<Announce>, PagingAndSortingRepository<Announce, Long>{
    
}
