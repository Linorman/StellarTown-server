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
    GET_USER_INFO_ERROR(429,"获取用户信息失败"),
    UPDATE_USER_INFO_SUCCESS(260,"更新用户信息成功"),
    UPDATE_USER_INFO_ERROR(431,"更新用户信息失败"),
    AVATAR_UPLOAD_SUCCESS(261,"头像上传成功"),
    AVATAR_UPLOAD_ERROR(432,"头像上传失败"),

    /**
     * 捐赠
     */
    GET_DONATE_INFO_SUCCESS(262,"获取捐赠信息成功"),
    GET_DONATE_INFO_ERROR(433,"获取捐赠信息失败"),

    /**
     * redis
     */
    REGISTER_NOT_NULL(440, "账号或者密码不能为空"),

    /**
     * oss
     */
    UPLOAD_SUCCESS(226,"上传成功"),
    UPLOAD_ERROR(441,"上传失败"),
    POST_UPLOAD_ERROR(442,"帖子上传失败"),
    POST_UPLOAD_SUCCESS(227,"帖子上传成功"),

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
    POST_LIKEPOST_EXIST(209, "帖子已点赞"),
    POST_LIKEPOST_NO(288, "帖子未点赞"),
    POST_FOLLOWER_NUM_GET_SUCCESS(209, "帖子关注数获取成功"),
    POST_FOLLOWER_NUM_GET_ERROR(411, "帖子关注数获取失败"),
    POST_NUM_GET_SUCCESS(210, "帖子数获取成功"),
    POST_NUM_GET_ERROR(412, "帖子数获取失败"),

    FOLLOW_USER_NOT_NULL(413,"关注用户不能为空"),
    FOLLOW_USER_NOT_SELF(414,"不能关注自己"),
    FOLLOW_USER_SUCCESS(215,"关注用户成功"),

    FOLLOW_USER_EXIST(298,"已关注该用户"),
    UNFOLLOW_USER_NOT_NULL(417,"取消关注用户不能为空"),
    UNFOLLOW_USER_NOT_SELF(418,"不能取消关注自己"),
    UNFOLLOW_USER_SUCCESS(219,"取消关注用户成功"),
    UNFOLLOW_USER_ERROR(288,"取消关注用户失败"),
    UNFOLLOW_USER_NOT_EXIST(297,"未关注该用户"),
    FOLLOWER_LIST_GET_SUCCESS(221,"关注列表获取成功"),
    FOLLOWER_LIST_GET_ERROR(422,"关注列表获取失败"),
    FOLLOWER_LIST_NULL(423,"关注列表为空"),
    FOLLOWER_NUM_GET_SUCCESS(224,"关注数获取成功"),
    FOLLOWER_NUM_GET_ERROR(424,"关注数获取失败"),
    FOLLOWER_NUM_NULL(425,"关注数为空"),
    USER_LIST_EMPTY(426,"用户列表为空"),
    ATTRACTION_GET_SUCCESS(299,"景点获取成功"),

    /**
     * 天气API
     */
    WEATHER_GET_SUCCESS(230,"天气获取成功"),
    WEATHER_GET_ERROR(430,"天气获取失败"),
    WEATHER_API_ERROR(431,"天气API错误"),
    CITY_NOT_NULL(432,"城市不能为空"),
    CITY_NOT_EXIST(433,"城市不存在"),
    ASTRONOMY_GET_SUCCESS(234,"天文获取成功"),
    ASTRONOMY_GET_ERROR(434,"天文获取失败"),
    CITY_NAME_GET_SUCCESS(235,"城市名称获取成功"),
    CITY_NAME_GET_ERROR(435,"城市名称获取失败"),
    LOCATION_GET_SUCCESS(236,"位置获取成功"),
    LOCATION_GET_ERROR(436,"位置获取失败"),

    /**
     * Openai
     */
    OPENAI_GET_SUCCESS(234,"Openai获取成功"),
    OPENAI_GET_ERROR(434,"Openai获取失败"),
    IMAGE_NOT_PNG(435,"图片格式不是png"),
    IMAGE_MAKE_SUCCESS(236,"图片生成成功"),
    IMAGE_MAKE_ERROR(436,"图片生成失败");


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

