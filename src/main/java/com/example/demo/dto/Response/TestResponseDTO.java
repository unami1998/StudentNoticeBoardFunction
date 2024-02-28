package com.example.demo.dto.Response;

import com.example.demo.dto.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
@Getter

public class TestResponseDTO extends ResponseDTO.Result {
    @Setter
    private Object request;
    private String isTest="true";
}
