package com.example.hateoas.commonweb;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    //관심있는 것들들
   String getComment();

    int getUp();

    int getDown();

    @Value("#{target.up + '  ' + target.down}")
    String getVotes();

    default String getVotes_2() {
        return getUp() + "  " +getDown();
    }
}
