import ballerina/http;
import ballerina/log;
import ballerina/mime;
import ballerina/openapi;

listener http:Listener ep0 = new(9090);

@openapi:ServiceInfo {
    title: "serviceName",
    serviceVersion: "1.0.0"
}
@http:ServiceConfig {
    basePath: "/"
}
service serviceName on ep0 {

    @openapi:ResourceInfo {
        summary: "Get operation for the path /user"
    }
    @http:ResourceConfig {
        methods:["GET"],
        path:"/user"
    }
    resource function resource1 (http:Caller outboundEp, http:Request _resource1Req) returns error? {
        http:Response _resource1Res = resource1(_resource1Req);
        _ = outboundEp->respond(_resource1Res);
    }

    @openapi:ResourceInfo {
        summary: "Put operation for the path /user"
    }
    @http:ResourceConfig {
        methods:["PUT"],
        path:"/user"
    }
    resource function resource2 (http:Caller outboundEp, http:Request _resource2Req) returns error? {
        http:Response _resource2Res = resource2(_resource2Req);
        _ = outboundEp->respond(_resource2Res);
    }

    @openapi:ResourceInfo {
        summary: "Post operation for the path /user"
    }
    @http:ResourceConfig {
        methods:["POST"],
        path:"/user"
    }
    resource function resource3 (http:Caller outboundEp, http:Request _resource3Req) returns error? {
        http:Response _resource3Res = resource3(_resource3Req);
        _ = outboundEp->respond(_resource3Res);
    }

}
