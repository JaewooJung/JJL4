package net.bitacademy.java41.vo;

import java.io.Serializable;
import java.sql.Date;

public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected int 		pno;
	protected String 	title;
	protected String 	content;
	protected Date 		startDate;
	protected Date 		endDate;
	protected String	tag;
	protected int level;
	protected String leader;
		
	public int getPno() {
		return pno;
	}

	public Project setPno(int pno) {
		this.pno = pno;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Project setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Project setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Project setStartDate(Date startDate) {
		this.startDate = startDate;return this;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Project setEndDate(Date endDate) {
		this.endDate = endDate;return this;
	}

	public String getTag() {
		return tag;
	}

	public Project setTag(String tag) {
		this.tag = tag;return this;
	}

	public int getLevel() {
		return level;
	}

	public Project setLevel(int level) {
		this.level = level;
		return this;
	}

	public String getLeader() {
		return leader;
	}

	public Project setLeader(String leader) {
		this.leader = leader;
		return this;
	}
	
	public Project clone() {
		Project obj = new Project();
		obj.pno = this.pno;
		obj.title = this.title;
		obj.content = this.content;
		obj.startDate = this.startDate;
		obj.endDate = this.endDate;
		obj.tag = this.tag;
		obj.level = this.level;
		obj.leader = this.leader;
		return obj;
	}
	
}
