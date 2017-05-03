package com.bawei.today.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/24 14:06
 */
@Table(name="shou",onCreated = "")
public class DbSqlite {

    @Column(name="id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="votecount")
    private String votecount;
    @Column(name="ptime")
    private String ptime;
    @Column(name="imgsrc")
    private String imgsrc;
    @Column(name="imgsrc1")
    private String imgsrc1;
    @Column(name="imgsrc2")
    private String imgsrc2;


    public DbSqlite(String title, String votecount, String ptime, String imgsrc, String imgsrc1, String imgsrc2) {
        this.title = title;
        this.votecount = votecount;
        this.ptime = ptime;
        this.imgsrc = imgsrc;
        this.imgsrc1 = imgsrc1;
        this.imgsrc2 = imgsrc2;

    }

    @Override
    public String toString() {
        return "DbSqlite{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", votecount='" + votecount + '\'' +
                ", ptime='" + ptime + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", imgsrc1='" + imgsrc1 + '\'' +
                ", imgsrc2='" + imgsrc2 + '\'' +
                '}';
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

    public String getVotecount() {
        return votecount;
    }

    public void setVotecount(String votecount) {
        this.votecount = votecount;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getImgsrc1() {
        return imgsrc1;
    }

    public void setImgsrc1(String imgsrc1) {
        this.imgsrc1 = imgsrc1;
    }

    public String getImgsrc2() {
        return imgsrc2;
    }

    public void setImgsrc2(String imgsrc2) {
        this.imgsrc2 = imgsrc2;
    }


}
