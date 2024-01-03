package entite.impl;

import entite.IPost;

public class Post implements IPost {
    
    private String idPost;
    private String title;
    private String body;

    public Post(String idPost, String title, String body) {
        this.idPost = idPost;
        this.title = title;
        this.body = body;
    }
    
    public Post() {
    	
    }
    @Override
    public String getIdPost() {
        return idPost;
    }

    @Override
    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }
}
