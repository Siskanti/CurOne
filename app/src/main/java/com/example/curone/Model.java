package com.example.curone;

public class Model {
    private String postingan;
    private String user;

    public Model() {
    }

    public Model(String user, String postingan) {
        this.user = user;
        this.postingan = postingan;
    }

    public String getPostingan() {
        return postingan;
    }

    public void setPostingan(String postingan) {
        this.postingan = postingan;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setKey(String key) {
    }
}
