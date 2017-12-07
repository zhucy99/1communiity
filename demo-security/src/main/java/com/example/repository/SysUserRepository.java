package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.entity.SysUser;


public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser>, PagingAndSortingRepository<SysUser, Long>{
    //继承 crudRepository, 实现分页功能
	SysUser findByUsername(String username);
}
