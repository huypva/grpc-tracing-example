package io.github.huypva.grpcserver.entrypoint;

import static org.junit.jupiter.api.Assertions.*;

import io.github.huypva.grpcserver.helloword.HelloRequest;
import io.github.huypva.grpcserver.helloword.HelloResponse;
import io.grpc.internal.testing.StreamRecorder;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author huypva
 */
@ExtendWith(MockitoExtension.class)
class GrpcControllerTest {

  @InjectMocks
  GrpcController grpcController;

  @Test
  void sayHello() throws Exception {
    HelloRequest helloRequest = HelloRequest.newBuilder().setName("World").build();
    StreamRecorder<HelloResponse> responseObserver = StreamRecorder.create();
    grpcController.sayHello(helloRequest, responseObserver);

    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      fail("The call did not terminate in time");
    }
    assertNull(responseObserver.getError());
    List<HelloResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    HelloResponse response = results.get(0);
    assertEquals(HelloResponse.newBuilder()
        .setMessage("Hello World!")
        .build(), response);
  }
}