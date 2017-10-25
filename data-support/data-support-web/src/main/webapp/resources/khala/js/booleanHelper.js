/*
 * @# booleanhelper.js Jan 20, 2009 10:01:24 AM
 * 
 * Copyright (C) 2007 - 2009 中国电信广东分公司China Telecom - Guangdong Telecom Co.Ltd.
 * All rights reserved!
 */

/**
 * boolean判断相关操作工具类
 * @author huang weijian
 */
var BooleanHelper = {};
/**
 * true的字符串型
 * 
 * @type String
 */
BooleanHelper.TRUE_STR = "true";
/**
 * false的字符串型
 * 
 * @type String
 */
BooleanHelper.FALSE_STR = "false";

/**
 * 判断字符串是否等于BooleanHelper.TRUE_STR('true')
 * 
 * @param {String}
 *            str
 * @return {Boolean} 若str为'true', 则返回true; 否则返回false
 */
BooleanHelper.isTrue = function(str) {
	return str ? str.toLowerCase() == BooleanHelper.TRUE_STR : false;
};

/**
 * 判断字符串是否等于BooleanHelper.FALSE_STR('false')
 * 
 * @param {String}
 *            str
 * @return {Boolean} 若str为'false', 则返回true; 否则返回false
 */
BooleanHelper.isFalse = function(str) {
	return str ? str.toLowerCase() == BooleanHelper.FALSE_STR : false;
};
