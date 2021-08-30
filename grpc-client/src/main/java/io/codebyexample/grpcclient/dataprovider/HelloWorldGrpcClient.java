package io.codebyexample.grpcclient.dataprovider;

import io.codebyexample.helloword.grpc.HelloRequest;
import io.codebyexample.helloword.grpc.HelloResponse;
import io.codebyexample.helloword.grpc.HelloWorldGrpc.HelloWorldBlockingStub;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author huypva
 */
@Service
public class HelloWorldGrpcClient implements HelloWorldClient {

  @GrpcClient("helloworld")
  private HelloWorldBlockingStub helloWorldBlockingStub;

  @Override
  public String hello(String name) {
    try {
      HelloRequest helloRequest = HelloRequest.newBuilder().setName(name).build();

      HelloResponse response = helloWorldBlockingStub.sayHello(helloRequest);
      return response.getMessage();
    } catch (final StatusRuntimeException e) {
      return "FAILED with " + e.getStatus().getCode().name();
    }
  }
}
