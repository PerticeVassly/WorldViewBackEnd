package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseVO {

    private Integer code;

    private String message;

    private Object data;

    public ResponseVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
