package com.hllwz.stellartownserver.common;

/**
 * 结果集枚举
 * @author Linorman
 * @version 1.0.0
 */
public enum ResultCode {
    /**
     * 默认成功值
     */
    SUCCESS(200, "响应成功"),

    /**
     * 数据库操作
     */
    DATABASE_SUCCESS(210, "数据库操作成功"),
    UPDATE_FAILURE(460,"更新失败"),

    /**
     * 用户操作
     */
    REGISTER_SUCCESS(221,"注册成功"),
    LOGIN_SUCCESS(222,"登陆成功"),
    LOGOUT_SUCCESS(223,"登出成功"),
    UPDATE_SUCCESS(224,"更新成功"),
    NEED_LOGIN(420,"需要登陆后操作"),
    ACCOUNT_EXIST(421,"账号已存在"),
    REQUIRE_USERNAME(422,"账号不能为空"),
    NO_OPERATOR_AUTH(423,"您的权限不够"),
    LOGIN_ERROR(424,"登陆失败"),
    LOGIN_ACCOUNT_OR_PASSWORD_ERROR(425,"用户名或者密码错误"),
    USER_NOT_EXIST(425,"用户不存在"),
    TOKEN_REFRESH_SUCCESS(426,"刷新token成功"),
    LOGOUT_ERROR(427,"登出失败,token不存在"),
    PARAM_IS_BLANK(428,"参数为空"),
    GET_USER_INFO_SUCCESS(225,"获取用户信息成功"),

    /**
     * redis
     */
    REGISTER_NOT_NULL(440, "账号或者密码不能为空"),

    /**
     * oss
     */
    UPLOAD_SUCCESS(226,"上传成功"),
    UPLOAD_ERROR(441,"上传失败"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(450,"系统错误"),

    /**
     * passage相关
     */
    POST_NOT_FOUND(401, "帖子不存在"),
    POST_LIST_NULL(402, "帖子列表为空"),
    POST_LIST_GET_SUCCESS(201, "帖子列表获取成功"),
    POST_LIST_GET_ERROR(403, "帖子列表获取失败"),
    POST_GET_SUCCESS(202, "帖子获取成功"),
    POST_GET_ERROR(404, "帖子获取失败"),
    POST_ADD_SUCCESS(203, "帖子发布成功"),
    POST_ADD_ERROR(405, "帖子添加失败"),
    POST_DELETE_SUCCESS(204, "帖子删除成功"),
    POST_DELETE_ERROR(406, "帖子删除失败"),
    POST_UPDATE_SUCCESS(205, "帖子更新成功"),
    POST_UPDATE_ERROR(407, "帖子更新失败"),
    POST_LIKE_SUCCESS(206, "帖子点赞成功"),
    POST_LIKE_ERROR(408, "帖子点赞失败"),
    POST_UNLIKE_SUCCESS(207, "帖子取消点赞成功"),
    POST_UNLIKE_ERROR(409, "帖子取消点赞失败"),
    POST_CANCEL_LIKE_ERROR(410, "帖子取消点赞失败"),
    POST_CANCEL_LIKE_SUCCESS(208, "帖子取消点赞成功"),
    POST_FOLLOWER_NUM_GET_SUCCESS(209, "帖子关注数获取成功"),
    POST_FOLLOWER_NUM_GET_ERROR(411, "帖子关注数获取失败"),
    POST_NUM_GET_SUCCESS(210, "帖子数获取成功"),
    POST_NUM_GET_ERROR(412, "帖子数获取失败"),
    FOLLOW_USER_NOT_NULL(413,"关注用户不能为空"),
    FOLLOW_USER_NOT_SELF(414,"不能关注自己"),
    FOLLOW_USER_SUCCESS(215,"关注用户成功"),
    FOLLOW_USER_EXIST(416,"已关注该用户"),
    UNFOLLOW_USER_NOT_NULL(417,"取消关注用户不能为空"),
    UNFOLLOW_USER_NOT_SELF(418,"不能取消关注自己"),
    UNFOLLOW_USER_SUCCESS(219,"取消关注用户成功"),
    UNFOLLOW_USER_NOT_EXIST(420,"未关注该用户"),
    FOLLOWER_LIST_GET_SUCCESS(221,"关注列表获取成功"),
    FOLLOWER_LIST_GET_ERROR(422,"关注列表获取失败"),
    FOLLOWER_LIST_NULL(423,"关注列表为空"),
    FOLLOWER_NUM_GET_SUCCESS(224,"关注数获取成功"),
    FOLLOWER_NUM_GET_ERROR(424,"关注数获取失败"),
    FOLLOWER_NUM_NULL(425,"关注数为空");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

