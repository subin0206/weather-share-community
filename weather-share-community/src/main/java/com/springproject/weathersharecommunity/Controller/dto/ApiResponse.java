package com.springproject.weathersharecommunity.Controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int status = 200;
    private String message = "OK";
    private String code = "200";
    private Object data = "no data";
}
