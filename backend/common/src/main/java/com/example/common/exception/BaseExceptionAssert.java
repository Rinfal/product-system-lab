package com.example.common.exception;

public interface BaseExceptionAssert {
    int getCode();
    String getMessage();


    //直接断言
    default void assertFail() {
        throw new BusinessException(getCode(), getMessage());
    }


    //带抛出时具体信息的断言
    default void assertFail(String message) {
        throw new BusinessException(getCode(), message);
    }

    //带判断是否为空
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw new BusinessException(getCode(), getMessage());
        }
    }

    default void assertNotVoid(Object obj) {
        if (obj == null ||
                (obj instanceof String && ((String) obj).trim().isEmpty()) ||
                (obj instanceof Integer && (Integer) obj == 0) ||
                (obj instanceof Long && (Long) obj == 0L)) {
            throw new BusinessException(getCode(), getMessage());
        }
    }

    default void assertNull(Object obj) {
        if (obj != null) {
            throw new BusinessException(getCode(), getMessage());
        }
    }



    default void assertNotFalse(Boolean bool) {
        if (!bool) {
            throw new BusinessException(getCode(), getMessage());
        }


    }


}
