package com.fxx.jwt;

/**
 * @program: zwhe
 * @description: 返回结果类
 * @author: DP_Li
 * @create: 2019/03/14
 */
public class CodeMsg {

    private String code;
    private String msg;
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg("0000", "服务端异常");
    public static CodeMsg SUCCESS = new CodeMsg("0001", "成功");
    public static CodeMsg ERROR = new CodeMsg("0002", "操作失败");
    public static CodeMsg PICTURE_ERROR = new CodeMsg("0003", "图片验证失败");
    public static CodeMsg LOGIN_FAILURE_ERROR = new CodeMsg("0004", "登录失败");
    public static CodeMsg REGISTER_ERROR = new CodeMsg("0005", "注册失败");
    public static CodeMsg LOGIN_CHECK_FAIL = new CodeMsg("0006", "请您先登录！");

    /*=======================================以下为业务需求新增======================================*/
    public static CodeMsg INPUT_PARAMETER_ISNULL = new CodeMsg("0007", "输入的参数为空");
    public static CodeMsg INPUT_FIELD_ISEMPTY = new CodeMsg("0008", "必填项有空字段");
    public static CodeMsg REPEAT_INSERT = new CodeMsg("0009", "重复插入,插入失败");
    public static CodeMsg NO_SHIPPER_OR_WAREHOUSE_FIND = new CodeMsg("0010", "找不到货主id或仓库id");
    public static CodeMsg PATERNER_ISDISABLE = new CodeMsg("0011", "合作商禁用");
    public static CodeMsg WAREHOUSEID_MISSING = new CodeMsg("0012", "仓库Id缺失");
    public static CodeMsg INSERT_FAILURE = new CodeMsg("0013", "新增失败");
    public static CodeMsg INSERTBATCH_FAILURE = new CodeMsg("0014", "批量新增失败");
    public static CodeMsg INSERT_RETABLE_FAILURE = new CodeMsg("0015", "新增中间表失败");
    public static CodeMsg UPDATE_FAILURE = new CodeMsg("0016", "修改失败");
    public static CodeMsg PUTAWAY_NO__MISSING = new CodeMsg("0017", "找不到入库单号");
    public static CodeMsg RESET_PASSWORD_FAILED = new CodeMsg("0018", "重置密码失败");
    public static CodeMsg INVALID_FORMAT = new CodeMsg("0019", "格式错误");


    public CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        String code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    public String toString() {
        return "CodeMsg [code=" + this.code + ", msg=" + this.msg + "]";
    }
}
