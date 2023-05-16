package io.github.huypva.grpcclient.dataprovider;

import io.github.huypva.grpc.helloworld.HelloRequest;
import io.github.huypva.grpc.helloworld.HelloResponse;
import io.github.huypva.grpc.helloworld.HelloWorldGrpc.HelloWorldBlockingStub;
import io.grpc.StatusRuntimeException;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author huypva
 */
@Setter
@Service
public class HelloWorldGrpcClient implements HelloWorldClient {

  @GrpcClient("grpc-server")
  private HelloWorldBlockingStub helloWorldBlockingStub;

  @Override
  public String hello(String name) {
      HelloRequest helloRequest = HelloRequest.newBuilder().setName(name).build();

      HelloResponse response = helloWorldBlockingStub.sayHello(helloRequest);
      return response.getMessage();
  }
}
