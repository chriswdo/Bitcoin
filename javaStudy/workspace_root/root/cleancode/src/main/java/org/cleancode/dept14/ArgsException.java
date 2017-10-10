package org.cleancode.dept14;

import static org.cleancode.dept14.ErrorCode.*;
public class ArgsException extends Exception{
	private char errorArgumentId = '\0';
	private String errorparameter = null;
	private String errorCode = OK;

}
