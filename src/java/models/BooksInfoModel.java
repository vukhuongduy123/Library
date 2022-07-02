package models;

import java.util.Date;

public class BooksInfoModel {
    private Integer id;
    private String name;
    private String authour;
    private String descr;
    private String categories;
    private String translator;
    private Date published;
    private Date addedTime;
    private Integer bookContentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthour() {
        return authour;
    }

    public void setAuthour(String authour) {
        this.authour = authour;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public Integer getBookContentId() {
        return bookContentId;
    }

    public void setBookContentId(Integer bookContentId) {
        this.bookContentId = bookContentId;
    }

    @Override
    public String toString() {
        return "BooksInfoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authour='" + authour + '\'' +
                ", descr='" + descr + '\'' +
                ", categories='" + categories + '\'' +
                ", translator='" + translator + '\'' +
                ", published=" + published +
                ", addedTime=" + addedTime +
                ", bookContentId=" + bookContentId +
                '}';
    }
}
