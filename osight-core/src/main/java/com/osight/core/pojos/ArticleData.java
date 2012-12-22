package com.osight.core.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.osight.framework.pojos.AuditableObject;

@Entity
@Table(name = "article")
public class ArticleData extends AuditableObject {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 1L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserData			user;

	@Column(name = "title", nullable = false)
	private String				title;

	@Column(name = "content", nullable = false)
	private String				content;

	@Column(name = "pv")
	private long				pv;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPv() {
		return pv;
	}

	public void setPv(long pv) {
		this.pv = pv;
	}

}
