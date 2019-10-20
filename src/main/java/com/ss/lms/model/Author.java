package com.ss.lms.model;

public class Author {

    private Integer authorId;

    private  String authorName;
    

    public Author(){}
    
    public Author(Integer auth, String authName){
    	authorId = auth;
    	authorName = authName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
