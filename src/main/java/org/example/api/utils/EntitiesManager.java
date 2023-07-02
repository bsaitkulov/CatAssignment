package org.example.api.utils;

import org.example.api.entities.Votes;

public class EntitiesManager {

    public static Votes createVote(){
        return Votes.builder()
                .imageId("asf2")
                .subId("my-user-1234")
                .value("1")
                .build();
    }
}
