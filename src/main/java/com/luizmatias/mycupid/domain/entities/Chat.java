package com.luizmatias.mycupid.domain.entities;

import java.util.Date;

public class Chat {
    private Long id;
    private User firstUser;
    private User secondUser;
    private Date createdAt;

    public Chat() {
    }

    public Chat(Long id, User firstUser, User secondUser, Date createdAt) {
        this.id = id;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
