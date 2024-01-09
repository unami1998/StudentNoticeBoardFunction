package com.example.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName="of")
public class responseDto {
    private String message;

    public responseDto(boolean isSuccess,String message){
        if(isSuccess){
            message = "success";
        }
        else{
            message = "unSuccess";
        }

    }


    public static responseDto test(String message){
        return new responseDto(false,"no"+message);
    }
}
