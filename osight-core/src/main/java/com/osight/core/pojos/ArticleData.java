package com.osight.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.osight.framework.pojos.AuditableObject;
import com.osight.history.annotation.HistoryProp;
import com.osight.history.annotation.NoHistory;

@Entity
@Table(name = "article")
@HistoryProp
@Component
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

	@OneToOne
	@JoinColumn(name = "category_id")
	private ArticleCategoryData	category;

	@Column(name = "pv")
	@NoHistory
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

	public ArticleCategoryData getCategory() {
		return category;
	}

	public void setCategory(ArticleCategoryData category) {
		this.category = category;
	}

}
