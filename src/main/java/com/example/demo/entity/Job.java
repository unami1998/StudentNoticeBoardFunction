package com.example.demo.entity;

import jakarta.persistence.Embeddable;
@Embeddable
public class Job {
    private String grade; //등급
    private String Volunteer; //봉사
    private String address; //주소
}
