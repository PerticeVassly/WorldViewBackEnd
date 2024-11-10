package org.interaction.interactionbackend.util;

import org.interaction.interactionbackend.vo.ResponseVO;

public class ResponseBuilder {
    public static ResponseVO buildSuccessResponse(String msg, Object data) {
        return new ResponseVO(1, msg, data);
    }

    public static ResponseVO buildErrorResponse(String msg, Object data) {
        return new ResponseVO(0, msg, data);
    }
}
