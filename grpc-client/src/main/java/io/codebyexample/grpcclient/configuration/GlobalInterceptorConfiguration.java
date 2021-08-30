package io.codebyexample.grpcclient.configuration;

import io.opentracing.Tracer;
import io.opentracing.contrib.grpc.TracingClientInterceptor;
import io.opentracing.contrib.grpc.TracingClientInterceptor.ClientRequestAttribute;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author huypva
 */
@Configuration(proxyBeanMethods = false)
public class GlobalInterceptorConfiguration {

  @Autowired
  private Tracer tracer;

  @GrpcGlobalClientInterceptor
  TracingClientInterceptor tracingServerInterceptor() {
    TracingClientInterceptor tracingInterceptor = TracingClientInterceptor
        .newBuilder()
        .withTracer(tracer)
        .withStreaming()
        .withTracedAttributes(ClientRequestAttribute.ALL_CALL_OPTIONS, ClientRequestAttribute.HEADERS)
        .build();

    return tracingInterceptor;
  }

}
