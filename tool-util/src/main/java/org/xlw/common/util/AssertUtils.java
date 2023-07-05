package org.xlw.common.util;

import java.util.*;

/**
 * Description: 断言使用utils
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/7/5 10:33
 */
/**
 * 断言工具类
 *
 */
public class AssertUtils {

    /**
     * 断言或字符串不为空，若为空抛出IllegalArgumentException类型异常
     *
     * @param obj
     * @param msg
     */
    public static void assertNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 断言或字符串为空，若不为空抛出IllegalArgumentException类型异常
     *
     * @param obj
     * @param msg
     */
    public static void assertNull(Object obj, String msg) {
        if (obj != null) {
            throw new IllegalArgumentException(msg + ", But was not null");
        }
    }

    /**
     * 断言为true，若不为true抛出IllegalArgumentException类型异常
     *
     * @param condition
     * @param msg
     */
    public static void assertTrue(boolean condition, String msg) {
        if (!condition) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 断言为false，若不为false抛出IllegalArgumentException类型异常
     *
     * @param condition
     * @param msg
     */
    public static void assertFalse(boolean condition, String msg) {
        if (condition) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * 判断某俩个字符串或对象相同，若不相同抛出IllegalArgumentException类型异常
     *
     * @param expected
     * @param actual
     * @param msg
     */
    public static void assertEquals(Object expected, Object actual, String msg) {
        if (!Objects.equals(expected, actual)) {
            throw new IllegalArgumentException(msg + ", Expected: " + expected + ", Actual: " + actual);
        }
    }

    /**
     * 判断某俩个字符串或对象不相同，若相同抛出IllegalArgumentException类型异常
     *
     * @param expected
     * @param actual
     * @param msg
     */
    public static void assertNotEquals(Object expected, Object actual, String msg) {
        if (Objects.equals(expected, actual)) {
            throw new IllegalArgumentException(msg + ", Expected and actual should not be equal.");
        }
    }

    /**
     * 断言俩个数组元素相同，若不相同抛出IllegalArgumentException类型异常
     *
     * @param expected
     * @param actual
     * @param msg
     */
    public static void assertArrayEquals(Object[] expected, Object[] actual, String msg) {
        if (!Arrays.equals(expected, actual)) {
            throw new IllegalArgumentException(msg + ", Expected: " + Arrays.toString(expected) + ", Actual: " + Arrays.toString(actual));
        }
    }

    /**
     * 断言俩个数组元素不相同，若相同抛出IllegalArgumentException类型异常
     *
     * @param expected
     * @param actual
     * @param msg
     */
    public static void assertArrayNotEquals(Object[] expected, Object[] actual, String msg) {
        if (Arrays.equals(expected, actual)) {
            throw new IllegalArgumentException(msg + ", Expected: " + Arrays.toString(expected) + ", Actual: " + Arrays.toString(actual));
        }
    }

    /**
     * 断言俩个对象相同，若不相同抛出IllegalArgumentException类型异常
     *
     * @param expected
     * @param actual
     * @param msg
     */
    public static void assertSame(Object expected, Object actual, String msg) {
        if (expected != actual) {
            throw new IllegalArgumentException(msg + ", Expected: " + expected + ", Actual: " + actual);
        }
    }

    /**
     * 断言俩个对象不相同，若相同抛出IllegalArgumentException类型异常
     *
     * @param expected
     * @param actual
     * @param msg
     */
    public static void assertNotSame(Object expected, Object actual, String msg) {
        if (expected == actual) {
            throw new IllegalArgumentException(msg + ", Expected and actual should not be the same.");
        }
    }

    /**
     * 断言错误，直接抛出IllegalArgumentException类型异常，打印fail信息
     *
     * @param msg
     */
    public static void fail(String msg) {
        throw new IllegalArgumentException(msg);
    }

}


