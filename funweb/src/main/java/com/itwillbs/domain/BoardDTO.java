package com.itwillbs.domain;

import java.sql.Timestamp;

public class BoardDTO {

	private int num;
	  private String name;
	  private String subject;
	  private String content;
	  private int readcount;
	  private Timestamp date;
	  //file 추가
	  private String file;
	  //답글 추가
	  private int re_ref;
	  private int re_lev;
	  private int re_sql;
	  



	public int getRe_ref() {
		return re_ref;
	}


	public int getRe_sql() {
		return re_sql;
	}


	public void setRe_sql(int re_sql) {
		this.re_sql = re_sql;
	}


	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}


	public int getRe_lev() {
		return re_lev;
	}


	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}




	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", name=" + name + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", date=" + date + ", file=" + file + ", re_ref=" + re_ref + ", re_lev="
				+ re_lev + ", re_sql=" + re_sql + "]";
	}
	  

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	  
	  
}
