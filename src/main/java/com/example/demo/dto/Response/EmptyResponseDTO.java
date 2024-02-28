package com.example.demo.dto.Response;

import com.example.demo.dto.ResponseDTO;
import lombok.Getter;

@Getter

public class EmptyResponseDTO extends ResponseDTO.Result {
    private String isEmpty="true";

}
