package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

@Getter
@Setter
@RequiredArgsConstructor(staticName="of")
public class ResponseDTO {
    private Result result=null;

    private String message;

    public ResponseDTO(boolean isSuccess, String message){
        if(isSuccess){
            message = "success";
        }
        else{
            message = "unSuccess";
        }

    }

    static abstract public class Result {
        @JsonIgnore
        private ResponseDTO responseDto = null;

        protected void SetParent(ResponseDTO responseDto) {
            this.responseDto = responseDto;
        }
        public ResponseDTO toResponseDTO(){
            return responseDto;
        }
        public static <T extends Result> T create(Class<T> clazz, boolean isSuccess, String message) {
            try {
                T currentInstance = (T) (clazz
                        .getDeclaredConstructor().newInstance());
                ResponseDTO responseDto = new ResponseDTO(false,message);
                currentInstance.SetParent(responseDto);
                responseDto.result = currentInstance;
                return currentInstance;
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            } catch (IllegalAccessException e) {
            }
            return null;

        }
    }


    public static ResponseDTO test(String message){
        return new ResponseDTO(false,"no"+message);
    }
}
