package io.github.huypva.grpcserver.entrypoint;

import io.github.huypva.grpcserver.helloword.HelloRequest;
import io.github.huypva.grpcserver.helloword.HelloResponse;
import io.github.huypva.grpcserver.helloword.HelloWorldGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author huypva
 */
@GrpcService
public class GrpcController extends HelloWorldGrpc.HelloWorldImplBase {

  @Override
  public void sayHello(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
    HelloResponse reply = HelloResponse.newBuilder().setMessage("Hello " + req.getName() + "!").build();
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}
