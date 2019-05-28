package project.module.exception.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.module.exception.LogicException;
import project.module.exception.exception.enums.ErrorCodeEnum;

public class UserException extends LogicException {

    private Logger logger = LoggerFactory.getLogger(UserException.class);

    public UserException(String errCode, String... params) {
        super(errCode, params);
    }

    public UserException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }


    public UserException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getErrCode(), errorCodeEnum.getErrorMsg());
    }

}
