package io.github.huypva.grpcclient.dataprovider;

import io.github.huypva.grpc.helloworld.HelloResponse;
import io.github.huypva.grpc.helloworld.HelloWorldGrpc.HelloWorldBlockingStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author huypva
 */
@ExtendWith(MockitoExtension.class)
class HelloWorldGrpcClientTest {

  @InjectMocks
  private HelloWorldGrpcClient helloWorldGrpcClient;

  //Refer to https://www.davidvlijmincx.com/posts/writing_higher_quality_tests_with_mockitos_inline_mock_maker/
  HelloWorldBlockingStub helloWorldBlockingStub = Mockito.mock(HelloWorldBlockingStub.class);


  @Test
  void hello() {
    HelloWorldGrpcClient helloWorldGrpcClient = new HelloWorldGrpcClient();
    helloWorldGrpcClient.setHelloWorldBlockingStub(helloWorldBlockingStub);

    HelloResponse response = HelloResponse.newBuilder()
        .setMessage("Hello world!")
        .build();
    Mockito.when(helloWorldBlockingStub.sayHello(Mockito.any())).thenReturn(response);

    Assertions.assertEquals("Hello world!", helloWorldGrpcClient.hello(""));
  }
}