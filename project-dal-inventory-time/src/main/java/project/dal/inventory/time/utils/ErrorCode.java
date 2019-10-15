//package project.dal.inventory.time.utils;
//
//import com.helijia.common.api.model.ApiException;
//import com.helijia.common.api.util.ApiCode;
//import com.helijia.common.api.util.ApiCodeUtil;
//
///**
// * @author fangfeng
// * @since 2018/3/15
// */
//public enum ErrorCode implements ApiCode {
//    INVENTORY_ALREADY_EXIST("38001"),    // 尝试创建的时间库存已经存在
//    INVENTORY_NOT_EXIST("38002"),        // 暂未生产时间
//    PARAM_CANNOT_BE_NULL("38003"),       // 变量%s(变量名)不能为NULL
//    CANNOT_FIND_POLICY("38004"),         // 找不到指定的策略: %s(策略名), %d(策略id)
//    OP_FAILED("38005"),                  // 操作失败, %s(原因)
//    INVALID("38006"),                    // 参数%s(参数名)非法
//    NOT_EQUAL("38007"),                  // %s(时间粒度|锁策略)不一致
//    LOCK_AND_RELEASE_FAILED("38008")     // 锁定&释放方法失败
//    ;
//
//    private static ApiCodeUtil codeUtil = new ApiCodeUtil("pivot/inventory/message/ErrorCode");
//    private String code;
//
//    private ErrorCode(String code) {
//        this.code = code;
//    }
//
//    @Override
//    public String getApiCode() {
//        return code;
//    }
//
//    @Override
//    public String getApiMessage(Object... params) throws ApiCodeException {
//        return codeUtil.getApiMessage(this.name(), params);
//    }
//
//    public static ApiException buildException(ErrorCode errorCode, Object... params) {
//        return new ApiException().build(errorCode.getApiCode(), errorCode.getApiMessage(params));
//    }
//}
