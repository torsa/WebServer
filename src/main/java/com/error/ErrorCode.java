package com.error;

/**
 * Error code enum.
 *
 * @author Torsa Das
 */
public enum ErrorCode {
    /**
     * BAD_REQUEST.
     */
    BAD_REQUEST("400", "Bad Request"),

    /**
     * NOT_FOUND.
     */
    NOT_FOUND("404", "Not Found");


    /**
     * Error Name.
     */
    private String errorName;

    /**
     * Error code.
     */
    private String code;

    /**
     * Constructor.
     * @param errorCode
     * @param name
     */
    ErrorCode(final String errorCode, final String name) {
        this.errorName = name;
        this.code = errorCode;
    }


    /**
     * Returns errroName.
     * @return errorName
     */
    public String getErrorName() {
        return errorName;
    }

    /**
     * Returns errroCode.
     * @return code
     */
    public String getCode() {
        return code;
    }

}
