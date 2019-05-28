package project.module.exception.exception.enums;

/**
 * 错误码枚举类型
 */
public enum ErrorCodeEnum {
    /**
     * 用户不存在.
     */
    USER_NOT_FOUND("100001","用户不存在"),
    /**
     * 用户状态异常.
     */
    USER_STATUS_FAILD("100002","用户状态异常");
    //...添加其他错误码

    private String errCode;

    private String errorMsg;
    ErrorCodeEnum(String errCode ,String errorMsg) {

        this.errCode = errCode;
        this.errorMsg = errorMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
