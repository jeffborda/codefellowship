package com.jeffborda.codefellowship;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;
    public String body;
    public Date postDate;

    @ManyToOne
    public ApplicationUser appUser;

    public Post() {}

    public Post(String body, Date postDate) {
        this.body = body;
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "POST(User:" + appUser.fullName + " | Post body: " + body + " | Post Date: " + postDate + ")";
    }
}
