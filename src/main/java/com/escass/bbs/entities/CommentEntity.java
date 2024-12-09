package com.escass.bbs.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"index"})
public class CommentEntity {
    private int index;
    private int articleIndex;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean isDeleted;
}
