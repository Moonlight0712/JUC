package com.lay.test;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class LogTest {

//  private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

  public static void main(String[] args) {
    log.info("日志记录开始工作");
    //模拟异常出现全局异常系统开始捕获异常！
    if (true) {
      throw new RuntimeException("厉不厉害你坤哥！");
    }
    log.info("不是，你干嘛嗨哎哟~");
  }
}
