package com.cloude.xmut.UserManage;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class Post extends BmobObject {


    private User author;//昵称

    private String content;//发布的内容

    private String title;//标题

    private BmobRelation likes;

    private BmobFile image;

    private BmobFile pic1; //图片
    private BmobFile pic2;
    private BmobFile pic3;
    private BmobFile pic4;
    private BmobFile pic5;
    private BmobFile pic6;
    private BmobFile pic7;
    private BmobFile pic8;
    private BmobFile pic9;

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }


    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    public BmobFile getImage() {
        return image;
    }

    public Post setImage(BmobFile image) {
        this.image = image;
        return this;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public Post setLikes(BmobRelation likes) {
        this.likes = likes;
        return this;
    }

    public BmobFile getPic1() {
        return pic1;
    }

    public void setPic1(BmobFile pic1) {
        this.pic1 = pic1;
    }

    public BmobFile getPic2() {
        return pic2;
    }

    public void setPic2(BmobFile pic2) {
        this.pic2 = pic2;
    }

    public BmobFile getPic3() {
        return pic3;
    }

    public void setPic3(BmobFile pic3) {
        this.pic3 = pic3;
    }

    public BmobFile getPic4() {
        return pic4;
    }

    public void setPic4(BmobFile pic4) {
        this.pic4 = pic4;
    }

    public BmobFile getPic5() {
        return pic5;
    }

    public void setPic5(BmobFile pic5) {
        this.pic5 = pic5;
    }

    public BmobFile getPic6() {
        return pic6;
    }

    public void setPic6(BmobFile pic6) {
        this.pic6 = pic6;
    }

    public BmobFile getPic7() {
        return pic7;
    }

    public void setPic7(BmobFile pic7) {
        this.pic7 = pic7;
    }

    public BmobFile getPic8() {
        return pic8;
    }

    public void setPic8(BmobFile pic8) {
        this.pic8 = pic8;
    }

    public BmobFile getPic9() {
        return pic9;
    }

    public void setPic9(BmobFile pic9) {
        this.pic9 = pic9;
    }


}
