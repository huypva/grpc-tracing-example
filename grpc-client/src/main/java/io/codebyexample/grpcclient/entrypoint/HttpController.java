package io.codebyexample.grpcclient.entrypoint;

import io.codebyexample.grpcclient.dataprovider.HelloWorldClient;
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

  @RequestMapping("/hello_a")
  public String helloword() throws InterruptedException {
    String serviceBMessage = helloWorldClient.hello("service_b");
    Thread.sleep(10);
    return serviceBMessage;
  }

}