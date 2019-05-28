package project.module.exception.enums;



public enum SysErrorCodeEnum {


    SYSTEM_ERROR("99999", "系统忙");


    private String errCode;

    private String errorMsg;

    private int status;



    SysErrorCodeEnum(String errCode,String errorMsg) {
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
