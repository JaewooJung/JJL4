package net.bitacademy.java41.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String 	email;
	private String 	name;
	private String 	password;
	private String 	tel;
	private String 	blog;
	private Date 	regDate;
	private Date 	updateDate;
	private int		addressNo;
	private String	detailAddress;
	private String	tag;
	private int	level;
	
	public int getLevel() {
		return level;
	}
	public Member setLevel(int level) {
		this.level = level;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getTel() {
		return tel;
	}
	public Member setTel(String tel) {
		this.tel = tel;
		return this;
	}
	public String getBlog() {
		return blog;
	}
	public Member setBlog(String blog) {
		this.blog = blog;
		return this;
	}
	public Date getRegDate() {
		return regDate;
	}
	public Member setRegDate(Date regDate) {
		this.regDate = regDate;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public Member setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	public int getAddressNo() {
		return addressNo;
	}
	public Member setAddressNo(int addressNo) {
		this.addressNo = addressNo;
		return this;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public Member setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
		return this;
	}
	public String getTag() {
		return tag;
	}
	public Member setTag(String tag) {
		this.tag = tag;
		return this;
	}
	
	
}
















