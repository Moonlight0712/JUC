package com.lay.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dragon code!
 * @create 2022-12-18 16:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String Message;
    private Integer ErrorCode;
}
