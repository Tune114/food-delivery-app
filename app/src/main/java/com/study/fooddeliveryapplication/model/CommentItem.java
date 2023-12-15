package com.study.fooddeliveryapplication.model;

public class CommentItem {
    private String cmtimage;
    private String cmtname;
    private String cmtcontent;
    private String phonenumber;

    public CommentItem() {
        // Constructor mặc định rỗng, cần thiết cho việc đọc dữ liệu từ Firebase
    }

    public CommentItem(String cmtimage, String cmtname, String cmtcontent, String phonenumber) {
        this.cmtimage = cmtimage;
        this.cmtname = cmtname;
        this.cmtcontent = cmtcontent;
        this.phonenumber = phonenumber;
    }

    public String getCmtimage() {
        return cmtimage;
    }

    public void setCmtimage(String cmtimage) {
        this.cmtimage = cmtimage;
    }

    public String getCmtname() {
        return cmtname;
    }

    public void setCmtname(String cmtname) {
        this.cmtname = cmtname;
    }

    public String getCmtcontent() {
        return cmtcontent;
    }

    public void setCmtcontent(String cmtcontent) {
        this.cmtcontent = cmtcontent;
    }
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
