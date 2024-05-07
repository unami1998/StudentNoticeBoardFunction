package com.example.demo.dto;

import com.example.demo.dto.Response.EmptyResponseDTO;
import com.example.demo.dto.Response.TestResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseDTO {
    private Header header=new Header();
    private Result result=null;
    private Custom custom=new Custom();

    @JsonIgnore
    public static ResponseDTO Unimplemented = new ResponseDTO(false,true,"unimplemented");
    @JsonIgnore
    public static ResponseDTO Success = new ResponseDTO(true,true,"normal");

    private ResponseDTO(boolean isSuccess, boolean isEmpty, String message) {
        if(isSuccess){
            header.type="success";
        }
        if(isEmpty){
            result=new EmptyResponseDTO();
        }
        header.message=message;
    }

    public static ResponseDTO test(String message) {
        return new ResponseDTO(false,true,"unimplemented : "+message);
    }
    public static ResponseDTO test(String message, Object... tag) {
        ResponseDTO dto= new ResponseDTO(false,false,"unimplemented : "+message);
        TestResponseDTO _result=new TestResponseDTO();
        _result.setRequest(tag);
        dto.result=_result;
        return dto;

    }

    public static Result create(boolean isSuccess, String message) {
        return Result.create(EmptyResponseDTO.class,isSuccess,message);
    }


    @Getter
    private static class ResultImpl extends Result {
        private final Object data;
        public ResultImpl(Object data) {
            this.data=data;
        }
    }
    public static ResponseDTO create(boolean isSuccess, String message, Object data) {
        ResponseDTO dto= new ResponseDTO(isSuccess,true,message);
        try {
            dto.result= new ResultImpl(data);
        } catch (Exception ignored) {
        }
        return dto;
    }

    public static ResponseDTO create(boolean isSuccess, String message, Class<?> clazz) {
        ResponseDTO dto= new ResponseDTO(isSuccess,true,message);
        try {
            dto.result= (Result) clazz.getConstructor().newInstance();
        } catch (Exception ignored) {
        }
        return dto;
    }

    @Getter
    static public class Header{
        private String type = "fail";
        private String message="empty";
    }
    @Getter
    static public class Item{
        private String key;
        private Object value;
        public Item(String key, Object value) {
            this.key=key;
            this.value=value;
        }
    }
    @Getter
    static public class Custom{
        @JsonIgnore
        private static ArrayList<Item> EmptyList=new ArrayList<>(0);
        private List<Item> items=EmptyList;
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
                ResponseDTO responseDto = new ResponseDTO(isSuccess,false,message);
                currentInstance.SetParent(responseDto);
                responseDto.result = currentInstance;
                return currentInstance;
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            } catch (IllegalAccessException e) {
            }
            return null;

        }


        public Result setCustomData(String key, Object value) {
            if(value==null)
                return this;
            if(responseDto.custom.items==Custom.EmptyList) {
                responseDto.custom.items = new ArrayList<>(0);
            }
            responseDto.custom.items.add(new Item(key,value));
            return this;
        }
    }

}
