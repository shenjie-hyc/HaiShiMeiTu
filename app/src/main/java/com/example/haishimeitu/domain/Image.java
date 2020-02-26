package com.example.haishimeitu.domain;

public class Image {
    // 我们这里只解析用到的数据
    /**
     * 图片Url
     */
    private String uri;

    public Image(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
