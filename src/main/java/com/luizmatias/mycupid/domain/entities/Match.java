package com.luizmatias.mycupid.domain.entities;

import java.util.Date;

public class Match {
    private Long id;
    private User clientUser;
    private User developerUser;
    private Date matchedAt;
    private Date unmatchedAt;

    public Match() {
    }

    public Match(Long id, User clientUser, User developerUser, Date matchedAt, Date unmatchedAt) {
        this.id = id;
        this.clientUser = clientUser;
        this.developerUser = developerUser;
        this.matchedAt = matchedAt;
        this.unmatchedAt = unmatchedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClientUser() {
        return clientUser;
    }

    public void setClientUser(User clientUser) {
        this.clientUser = clientUser;
    }

    public User getDeveloperUser() {
        return developerUser;
    }

    public void setDeveloperUser(User developerUser) {
        this.developerUser = developerUser;
    }

    public Date getMatchedAt() {
        return matchedAt;
    }

    public void setMatchedAt(Date matchedAt) {
        this.matchedAt = matchedAt;
    }

    public Date getUnmatchedAt() {
        return unmatchedAt;
    }

    public void setUnmatchedAt(Date unmatchedAt) {
        this.unmatchedAt = unmatchedAt;
    }
}
