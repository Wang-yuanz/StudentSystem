package com.alvis.exam.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Capter {
    private Integer id;

    private String chapterName;

    private String chapter;

    private Integer chapterHours;

    private Date createTime;

    private Date updateTime;
    private Boolean deleted;


}