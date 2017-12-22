package com.example.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.entity.announce.Announce;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SysUser implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 920684093245260702L;
	@Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String adress;
    private String telephone;
    private String mail;
    private String wechat;
    
    @OneToMany(mappedBy="author",cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JsonBackReference // 避免json 的无限循环
    private List<Announce> announces;
    
    @OneToMany(mappedBy="author",cascade= {CascadeType.REFRESH},fetch=FetchType.LAZY)
    @JsonManagedReference// 避免json 的无限循环
	private List<Comment> comments;
    
    public List<Announce> getAnnounces() {
		return announces;
	}

	public void setAnnounces(List<Announce> announces) {
		this.announces = announces;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	

    //CascadeType.REFRESH 详解 http://westerly-lzh.github.io/cn/2014/12/JPA-CascadeType-Explaining/
    //https://openhome.cc/Gossip/EJB3Gossip/CascadeTypeFetchType.html
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
    
}