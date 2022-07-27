package io.github.huypva.grpcclient.entrypoint;

import io.github.huypva.grpcclient.dataprovider.HelloWorldClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huypva
 */
@Slf4j
@RestController
public class HttpController {

  @Autowired
  private HelloWorldClient helloWorldClient;

  @RequestMapping("/hello")
  public String helloword() throws InterruptedException {
    String serviceBMessage = helloWorldClient.hello("server");
    Thread.sleep(10);
    return serviceBMessage;
  }

}