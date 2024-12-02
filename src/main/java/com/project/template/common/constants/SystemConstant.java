package com.project.template.common.constants;

public class SystemConstant {
	private SystemConstant() {
	}

	/**
	 * Character
	 */
	public static final String CHARACTER_NULL = "";
	public static final String CHARACTER_DECIMAL_POINT = ".";
	public static final String CHARACTER_FORWARD_SLASH = "/";
	public static final String CHARACTER_BACK_SLASH = "\\";
	public static final String CHARACTER_HYPHEN = "-";
	public static final String CHARACTER_UNDERLINE = "_";
	public static final String CHARACTER_ASTERISK = "*";
	public static final String CHARACTER_SPACE = " ";
	public static final String CHARACTER_PERCENT_SIGN = "%";
	public static final String CHARACTER_COLON = ":";
	public static final String CHARACTER_EMAIL = "@";
	public static final String CHARACTER_LEFT_BRACKET = "(";
	public static final String CHARACTER_RIGHT_BRACKET = ")";
	public static final String PATTERN = "/**";

	/**
	 * Long
	 */
	public static final Long ADMIN_ID = 1L;
	public static final Long DEFAULT_ROLE_ADMIN = 1L;
	public static final Long DEFAULT_ROLE_USER = 2L;
	public static final Long DEFAULT_ROLE_REGULAR_MEMBER = 3L;
	public static final Long DEFAULT_ROLE_PREMIUM_MEMBER = 4L;


	public static final String AUTHORIZE_KEY = "Authorization";
	public static final String AUTHORIZE_HEADER = "Bearer ";

	/**
	 * DateFormat
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String RENAME_DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";

	/**
	 * Access Token 过期时间
	 */
	public static Long ACCESS_TOKEN_EXPIRE_TIME = 14 * 24 * 60 * 1000L;
	/**
	 * Refresh Token 过期时间
	 */
	public static Long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;
}
