/*
 * Created on 2012-12-28
 */
package com.osight.core.pojos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.osight.framework.pojos.AuditableObject;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "article_category")
@Component
public class ArticleCategoryData extends AuditableObject {
	private static final long	serialVersionUID	= 1L;

	public ArticleCategoryData() {
	}

	public ArticleCategoryData(long id) {
		this.id = id;
	}

	public ArticleCategoryData(String name) {
		this.name = name;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	@Column(name = "name", nullable = false)
	private String				name;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<ArticleData>	articles;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ArticleData> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleData> articles) {
		this.articles = articles;
	}

}
