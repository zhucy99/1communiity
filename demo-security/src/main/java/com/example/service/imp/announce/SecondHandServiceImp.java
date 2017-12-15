package com.example.service.imp.announce;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Picture;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.entity.announce.Announce;
import com.example.entity.announce.SecondHand;
import com.example.repository.SysRoleRepository;
import com.example.repository.announce.SecondHandRepository;
import com.example.service.RoleService;
import com.example.service.announce.SecondHandService;

@Service
public class SecondHandServiceImp implements SecondHandService {
	
	@Autowired
	SecondHandRepository secondHandRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public Page<SecondHand> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SecondHand> findSearch(SecondHand model, int page, int size) {

		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Page<SecondHand> result = secondHandRepository.findAll(new Specification<SecondHand>() {

			@Override
			public Predicate toPredicate(Root<SecondHand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (model.getTitle() != null) {
					list.add(cb.like(root.get("title").as(String.class), "%" + model.getTitle() + "%"));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		}, pageable);

		return result;
	}

	@Override
	public Page<SecondHand> add(SecondHand secondHand, SysUser user, MultipartFile[] files) {
		
		List<Picture> pics = null;
		
		if(files != null && files.length > 0){
			String userImgPath = "C:\\image";//获取项目动态绝对路径
			pics = new ArrayList<Picture>();
            int num = 0;
            for(int i = 0; i < files.length;i++){
                if (!files[i].isEmpty()) {
                    //获取源文件名
                    String orgName = files[i].getOriginalFilename();
                    System.out.println("原文件名：" + orgName);
                    String saveName = UUID.randomUUID() + orgName.substring(orgName.lastIndexOf("."));
                    try {
                    	
                        files[i].transferTo(new File(userImgPath+"/"+ saveName));
                        
                        Picture pic = new Picture();
                        pic.setFileName(saveName);
                        pic.setSecondHand(secondHand);
                        
                        pics.add(pic);
                    } catch (IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }
                    num += 1;
                }
            }
        }
		
		secondHand.setCreateTime(new Date());
		secondHand.setAuthor(user);
		secondHand.setType(Announce.TYPE_SECONDHAND);
		secondHand.setStatus(Announce.STATUS_CREATE);
		if(pics!=null) {
			secondHand.setPictures(pics);
		}
		secondHandRepository.save(secondHand);
		return null;
	}

	@Override
	public Page<SecondHand> delete(SecondHand model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecondHand findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SecondHand findById(Long id) {
		return this.secondHandRepository.findOne(id);
	}

}
