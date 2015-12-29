package com.eshraq.gosport.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ahmed on 12/29/2015.
 */
@DatabaseTable(tableName = "comment")
public class Comment {
    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "author_id")
    private User author;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "article_id")
    private Article article;

    @DatabaseField(columnName = "created")
    private Long created;


    ///////////////////////////////////////////////////////////////////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}
