package io.codebyexample.grpcserver.entrypoint;

import io.codebyexample.helloword.grpc.HelloRequest;
import io.codebyexample.helloword.grpc.HelloResponse;
import io.codebyexample.helloword.grpc.HelloWorldGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author huypva
 */
@GrpcService
public class GrpcController extends HelloWorldGrpc.HelloWorldImplBase {

  @Override
  public void sayHello(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
    HelloResponse reply = HelloResponse.newBuilder().setMessage("Hello ==> " + req.getName()).build();
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}
