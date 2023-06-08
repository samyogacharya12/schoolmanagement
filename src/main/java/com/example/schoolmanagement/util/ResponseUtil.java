package com.example.schoolmanagement.util;


import com.example.schoolmanagement.dto.RestResponse;
import com.example.schoolmanagement.enumconstants.ResponseStatus;
import com.example.schoolmanagement.exception.ErrorConstants;
import org.springframework.http.server.ServerHttpResponse;
import org.zalando.problem.DefaultProblem;

import java.util.List;


/**
 * Utility class to wrap response.
 */
public class ResponseUtil {

    public static RestResponse getErrorResponse(DefaultProblem defaultProblem, String message) {
        if (defaultProblem.getStatus() != null && defaultProblem.getStatus().getStatusCode() == 400)
            return getBadRequestResponse(message, null);
        else if (defaultProblem.getStatus() != null && defaultProblem.getStatus().getStatusCode() == 404)
            return getNotFoundResponse();
        return getInternalServerErrorResponse(ErrorConstants.DEFAULT_INTERNAL_SERVER_ERROR_MESSAGE);
    }

    public static RestResponse getNotFoundResponse() {
        return new RestResponse()
                .setMessage(ErrorConstants.DEFAULT_NOT_FOUND_ERROR_MESSAGE)
                .setResponseStatus(ResponseStatus.RESOURCE_NOT_FOUND)
                .setStatus(ResponseStatus.RESOURCE_NOT_FOUND.getValue());
    }

    public static RestResponse getUnAuthorizedResponse() {
        return new RestResponse()
                .setMessage(ErrorConstants.DEFAULT_UNAUTHORIZED_ERROR_MESSAGE)
                .setResponseStatus(ResponseStatus.UNAUTHORIZED_USER)
                .setStatus(ResponseStatus.UNAUTHORIZED_USER.getValue());
    }

    public static RestResponse getUnAuthorizedResponse(String message) {
        return new RestResponse()
                .setMessage(message)
                .setResponseStatus(ResponseStatus.UNAUTHORIZED_USER)
                .setStatus(ResponseStatus.UNAUTHORIZED_USER.getValue());
    }

    public static RestResponse getConflictsResponse(String message) {
        return new RestResponse()
                .setMessage(message)
                .setResponseStatus(ResponseStatus.UNAUTHORIZED_USER)
                .setStatus(ResponseStatus.UNAUTHORIZED_USER.getValue());
    }

    public static RestResponse getSuccessResponse(Object body, String message) {
        return new RestResponse()
                .setMessage(message)
                .setDetail(body)
                .setResponseStatus(ResponseStatus.SUCCESS)
                .setStatus(ResponseStatus.SUCCESS.getValue());
    }

    public static RestResponse getBadRequestResponse(String message, Object detail) {
        return new RestResponse()
                .setMessage(message)
                .setResponseStatus(ResponseStatus.BAD_REQUEST)
                .setStatus(ResponseStatus.BAD_REQUEST.getValue())
                .setDetail(detail);
    }

    public static RestResponse getInternalServerErrorResponse(String message) {
        return new RestResponse()
                .setMessage(message)
                .setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR)
                .setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getValue());
    }

    public static String getResponseMessage(ServerHttpResponse response, boolean success) {
        String message = getMessageFromHeader(response);
        if (message == null) {
            if (success) message = "Success";
            else message = "Failure";
        }
        return message;
    }

    private static String getMessageFromHeader(ServerHttpResponse response) {
        List<String> messageList = response.getHeaders().get("X-" + HeaderUtil.APPLICATION_NAME + "-message");
        if (messageList != null)
            return messageList.get(0);
        return null;
    }
}
