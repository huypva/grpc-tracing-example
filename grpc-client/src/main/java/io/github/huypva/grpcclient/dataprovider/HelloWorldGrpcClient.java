package io.github.huypva.grpcclient.dataprovider;

import io.github.huypva.grpc.helloworld.HelloRequest;
import io.github.huypva.grpc.helloworld.HelloResponse;
import io.github.huypva.grpc.helloworld.HelloWorldGrpc.HelloWorldBlockingStub;
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
