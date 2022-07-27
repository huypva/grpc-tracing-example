package io.github.huypva.grpcserver.configuration;

import io.opentracing.Tracer;
import io.opentracing.contrib.grpc.TracingServerInterceptor;
import io.opentracing.contrib.grpc.TracingServerInterceptor.ServerRequestAttribute;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author huypva
 */
@Configuration(proxyBeanMethods = false)
public class GlobalInterceptorConfiguration {

  @Autowired
  private Tracer tracer;

  @GrpcGlobalServerInterceptor
  TracingServerInterceptor tracingServerInterceptor() {
    TracingServerInterceptor tracingInterceptor = TracingServerInterceptor
        .newBuilder()
        .withTracer(tracer)
        .withStreaming()
        .withVerbosity()
        .withTracedAttributes(ServerRequestAttribute.HEADERS,
            ServerRequestAttribute.METHOD_TYPE)
        .build();

    return tracingInterceptor;
  }


}
