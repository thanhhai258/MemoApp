package com.example.memoapp;

public class MemoItem {
    private int mId;
    private String mTilte;
    private String mContent;

    public MemoItem() {

    }

    public MemoItem(String mTilte, String mContent) {
        this.mTilte = mTilte;
        this.mContent = mContent;
    }

    public MemoItem(int mId, String mTilte, String mContent) {
        this.mId = mId;
        this.mTilte = mTilte;
        this.mContent = mContent;
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTilte() {
        return mTilte;
    }

    public void setmTilte(String mTilte) {
        this.mTilte = mTilte;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
