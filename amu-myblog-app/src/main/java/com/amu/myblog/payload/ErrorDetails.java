package com.amu.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Created 2022/06/21
 * @Author Amu
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
