package project.module.exception.message;


public interface LogicExceptionMessage {

    /**
     * 获取异常消息内容
     * @param errCode 错误码
     * @return
     */
    public String getMessage(String errCode);
}
