package com.study.fooddeliveryapplication.model;

public class CommentItem {
    private String cmtimage;
    private String cmtname;
    private String cmtcontent;

    public CommentItem() {
        // Constructor mặc định rỗng, cần thiết cho việc đọc dữ liệu từ Firebase
    }

    public CommentItem(String cmtimage, String cmtname, String cmtcontent) {
        this.cmtimage = cmtimage;
        this.cmtname = cmtname;
        this.cmtcontent = cmtcontent;
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
}
