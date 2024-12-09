package com.escass.bbs.vos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo {
    public final int countPerPage = 5;
    public final int page;
    public final int totalCount;
    public final int movableMinPage = 1;
    public final int movableMaxPage;
    public final int offsetCount;

    public BoardVo(int page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;
        this.movableMaxPage = totalCount / this.countPerPage == 0 ? totalCount / this.countPerPage : totalCount / this.countPerPage + 1 ;
        this.offsetCount = (page - 1) * this.countPerPage;
    }
}
