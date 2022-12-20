package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestAddComents {

    /**
     * NewsId : 84
     * UserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
     * Comment : Good News
     * ParentCommentId : 1
     */

    private int NewsId;
    private String UserId;
    private String Comment;
    private int ParentCommentId;

    public int getNewsId() {
        return NewsId;
    }

    public void setNewsId(int NewsId) {
        this.NewsId = NewsId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public int getParentCommentId() {
        return ParentCommentId;
    }

    public void setParentCommentId(int ParentCommentId) {
        this.ParentCommentId = ParentCommentId;
    }
}
