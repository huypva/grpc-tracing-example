package io.github.huypva.grpcserver.configuration;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;


@Slf4j
@GrpcGlobalServerInterceptor
public class HeaderLogInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers,
            ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info("Headers:" + headers);
        return serverCallHandler.startCall(serverCall, headers);
    }
    
}
