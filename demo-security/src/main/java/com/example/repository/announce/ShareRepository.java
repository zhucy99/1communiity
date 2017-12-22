package com.example.repository.announce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.entity.announce.Share;

public interface ShareRepository extends JpaRepository<Share, Long>, JpaSpecificationExecutor<Share>, PagingAndSortingRepository<Share, Long>{
    
}
