package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyAccountInfoDTO implements StudentAccount {
    private Long id;
    private String nickName;
    private String email;

    public long getId() {
        return id != null ? id : 0L; // id가 null일 때 기본값 반환
    }
    public void setId(Long id){
        this.id=id;
    }
    @Override
    public String toString() {
        return "MyAccountInfoDTO{" +
                "nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
