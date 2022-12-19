package com.lay.controller;

import com.lay.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/api/demo")
public class DemoController {
    @GetMapping("/getMessage")
    public ErrorResponse getTest() {
        log.info("日志记录开始工作");
        //模拟异常出现全局异常系统开始捕获异常！
        if (true) {
            throw new RuntimeException("厉不厉害你坤哥！");
        }
        return new ErrorResponse("返回数据正常！", 200);
    }

}
