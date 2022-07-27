package io.github.huypva.grpcclient.entrypoint;

import static org.junit.jupiter.api.Assertions.*;

import io.github.huypva.grpcclient.dataprovider.HelloWorldClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

/**
 * @author huypva
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private HelloWorldClient helloWorldClient;

  @Test
  void hellowordTest() {
    Mockito.when(helloWorldClient.hello(Mockito.anyString())).thenReturn("Hello world!");

    ResponseEntity<String> response = restTemplate.getForEntity("http://127.0.0.1:" + port + "/hello", String.class);
    Assertions.assertEquals("Hello world!", response.getBody());
  }
}