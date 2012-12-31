package com.osight.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.osight.framework.pojos.AuditableObject;

@Entity
@Table(name = "article_comment")
public class ArticleCommentData extends AuditableObject {

	private static final long	serialVersionUID	= 1L;
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	@Column(name = "user_name")
	private String				userName;

	@Column(name = "email")
	private String				email;

	@Column(name = "article_id", nullable = false)
	private long				articleId;

	@Column(name = "content", nullable = false)
	private String				content;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
