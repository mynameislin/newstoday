package com.bawei.today.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/24 14:53
 */

public class Dbguan {

    private int id;
    private String title;

    public Dbguan(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Dbguan{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
