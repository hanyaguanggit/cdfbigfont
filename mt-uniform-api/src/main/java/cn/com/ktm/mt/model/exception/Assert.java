package cn.com.ktm.mt.model.exception;


import java.util.Collection;

/**
 * 服务断言.
 *
 */
public final class Assert {
    /** 构造函数. */
    private Assert() {

    }
    
  

    /**
     * 断言失败,简单抛出ServiceAssertError异常.
     *
     * @param reason
     *            失败原因
     * @param t
     *            嵌套异常
     * @param message
     *            错误描述
     */
    public static void fail(int errcode,String partnerId,Long channelId,String fileName) {
        throw new AssertError(errcode, partnerId, channelId,fileName);
    }

    /**
     * 断言失败,简单抛出{ServiceAssertError}异常.
     *
     * @param reason
     *            失败原因
     */
    public static void fail(int reason) {
        throw new AssertError(reason);
    }

    /**
     * 断言失败,简单抛出{ServiceAssertError}异常.
     *
     * @param errcode
     *            失败原因
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    /*public static void fail(int reason, String format, Object... args) {
        if (null != format) {
            throw new AssertError(reason, String.format(format, args));
        } else {
            throw new AssertError(reason);
        }
    }*/

    public static void fail(int errcode, String format, Object... args) {
        if(format!=null && !"".equals(format)){
            throw new AssertError(errcode,String.format(format,args));
        }else{
            throw new AssertError(errcode, args);
        }
    }

    /**
     *
     * @param reason
     *            失败原因
     * @param t
     *            嵌套异常
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void fail(int reason, Throwable t, String format, Object... args) {
        fail(reason, t, String.format(format, args));
    }

    /**
     * 断言失败,简单抛出ServiceAssertError异常.
     *
     * @param reason
     *            失败原因
     * @param t
     *            嵌套异常
     * @param message
     *            错误描述
     */
    public static void fail(int reason, Throwable t, String message) {
        throw new AssertError(reason, message, t);
    }

    /**
     * 断言测试的值为true，否则抛出ServiceAssertError异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void isTrue(boolean value, int errcode) {
        isTrue(value, errcode, null);
    }

    /**
     * 断言测试的值为true，否则抛出ServiceAssertError异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void isTrue(boolean value, int errcode, String format, Object... args) {

        if (!value) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }

    }


    /**
     * 断言测试的值为true，否则抛出ServiceError异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void isTrue(boolean value, int errcode, String message) {
        if (!value) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言测试的值为false，否则抛出ServiceError异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void isFalse(boolean value, int errcode) {
        isFalse(value, errcode, null);
    }

    /**
     *
     * 断言测试的值为false，否则调用抛出ServiceError异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void isFalse(boolean value, int errcode, String format, Object... args) {
        if (value) {
//            fail(errcode, String.format(format, args));
            fail(errcode,format, args);
        }
    }


    /**
     * 断言测试的值为false，否则调用抛出ServiceError异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void isFalse(boolean value, int errcode, String message) {
        if (value) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言测试的值为null，否则调用{@link #fail(int)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static <T> void isNull(final T value, int errcode) {
        isNull(value, errcode, null);
    }

    /**
     * 断言测试的值为null，否则调用抛出ServiceError异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static <T> void isNull(final T value, int errcode, String format, Object... args) {

        if (null != value) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }

    }


    /**
     * 断言测试的值为null，否则调用{#fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static <T> void isNull(final T value, int errcode, String message) {
        if (null != value) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言测试的值不为null，否则调用{@link #fail(int)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static <T> void notNull(String partnerId,Long channelId,String fileName,final T value, int errorCode,String message) {
    	 if (null == value||"".equals(value)) {
             fail(errorCode,fileName, partnerId,channelId,  message);
         }
    }
    
    public static void fail(int errorCode,String fileName,String partnerId,Long channelId,String message) {
            throw new AssertError(errorCode ,fileName, partnerId,channelId,message);
      
    }
    
    /**
     * 断言测试的值不为null，否则调用{@link #fail(int)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static <T> void notNull(final T value, int errcode) {
        notNull(value, errcode, null);
    }
    
    

    public static void notEmpty(Collection<?> toTest, int errcode) {
        notEmpty(toTest, errcode, null);
    }

    /**
     *
     *
     * 断言测试的值不为null，否则调用{#fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static <T> void notNull(final T value, int errcode, String format, Object... args) {

        if (null == value||"".equals(value)) {
            fail(errcode, format, args);
        }
    }


    /**
     * 断言测试的值不为空，否则调用{ fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
//     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static <T> void notEmpty(final Collection<T> value, int errcode, String message) {
        if (null == value || value.isEmpty()) {
            fail(errcode, message);
        }
    }*/

    public static <T> void notEmpty(final Collection<T> value, int errcode, String format,Object... args) {
        if (null == value || value.isEmpty()) {
            fail(errcode, format,args);
        }
    }

    /**
     * 断言测试的值不为null，否则调用{ fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    public static <T> void not(final T value, int errcode, String message) {
        if (null == value) {
            fail(errcode, message);
        }
    }

    /**
     * 断言测试的字符串不为空，否则调用{@link #fail(int)}抛出异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void notBlank(String value, int errcode) {
        notBlank(value, errcode, null);
    }

    /**
     *
     * 断言测试的字符串不为空，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void notBlank(String value, int errcode, final String format, Object... args) {

        if (null == value || value.trim().length() == 0) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }


    /**
     * 断言测试的字符串不为空，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void notBlank(String value, int errcode, final String message) {
        if (null == value || value.trim().length() == 0) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value == expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void eq(int value, int expect, int errcode) {
        eq(value, expect, errcode, null);
    }

    /**
     * 断言value == expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void eq(int value, int expect, int errcode, final String message) {
        if (expect != value) {
            fail(errcode, message);
        }
    }*/

    /*public static void eq(int value, int expect, int errcode, String format,Object... params) {
        if (expect != value) {
            fail(errcode, format,params);
        }
    }*/

    /**
     * 断言value == expect，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param expect
     *            断言的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    /*public static <T> void eq(T value, T expect, int errcode, String format, Object... args) {
        if (args.length == 0) {
            eq(value, expect, errcode, format);
        } else {
            eq(value, expect, errcode, String.format(format, args));
        }

    }*/

    public static <T> void eq(T value, T expect, int errcode, String format,Object... args) {
        if (value == null && expect == null) {
            return;
        }
        if (value != null) {
            if (!value.equals(expect)) {
                fail(errcode, format,args);
            }
        } else {
            if (!expect.equals(value)) {
                fail(errcode, format,args);
            }
        }

    }


    /**
     * 断言value == expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            待测试的值
     * @param expect
     *            断言的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            消息
     */
    /*private static <T> void eq(T value, T expect, int errcode, final String message) {
        if (value == null && expect == null) {
            return;
        }
        if (value != null) {
            if (!value.equals(expect)) {
                fail(errcode, message);
            }
        } else {
            if (!expect.equals(value)) {
                fail(errcode, message);
            }
        }
    }*/

    /**
     * 断言value != expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void ne(String value, String expect, int errcode) {
        ne(value, expect, errcode, null);
    }

    /**
     * 断言value != expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void ne(String value, String expect, int errcode, String format, Object... args) {
//        ne(value, expect, errcode, String.format(format, args));
        ne(value, expect, errcode, format, args);
    }


    /**
     * 断言value != expect，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void ne(String value, String expect, int errcode, final String message) {

        if (expect == null && value == null) {
            return;
        }

        if ((null != expect && expect.equalsIgnoreCase(value)) || (null != value && value.equalsIgnoreCase(expect))) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value != expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void ne(int value, int expect, int errcode) {
        ne(value, expect, errcode, null);
    }

    /**
     * 断言value != expect，否则调用{#fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void ne(int value, int expect, int errcode, String format, Object... args) {

        if (expect == value) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }


    /**
     * 断言value != expect，否则调用{fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void ne(int value, int expect, int errcode, final String message) {

        if (expect == value) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value > expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void gt(int value, int expect, int errcode) {
        gt(value, expect, errcode, null);
    }

    /**
     * 断言value > expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void gt(int value, int expect, int errcode, String format, Object... args) {
        if (value <= expect) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }

    /**
     * 断言value > expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void gt(int value, int expect, int errcode, final String message) {
        if (value <= expect) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value >= expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void egt(int value, int expect, int errcode) {
        egt(value, expect, errcode, null);
    }

    /**
     * 断言value >= expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void egt(int value, int expect, int errcode, String format, Object... args) {
        if (value < expect) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }

    /**
     * 断言value >= expect，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void egt(int value, int expect, int errcode, final String message) {
        if (value < expect) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value < expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void lt(int value, int expect, int errcode) {
        lt(value, expect, errcode, null);
    }

    /**
     * 断言value < expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void lt(int value, int expect, int errcode, String format, Object... args) {
        if (value >= expect) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }

    /**
     * 断言value < expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void lt(long value, long expect, int errcode, final String message) {
        if (value >= expect) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value < expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void lt(long value, long expect, int errcode) {
        lt(value, expect, errcode, null);
    }

    /**
     * 断言value < expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void lt(long value, long expect, int errcode, String format, Object... args) {
        if (value >= expect) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }


    /**
     * 断言value < expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void lt(int value, int expect, int errcode, final String message) {
        if (value >= expect) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value <= expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void elt(int value, int expect, int errcode) {
        elt(value, expect, errcode, null);
    }

    /**
     * 断言value <= expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void elt(int value, int expect, int errcode, String format, Object... args) {
        if (value > expect) {
//            fail(errcode, String.format(format, args));
            fail(errcode,format, args);
        }
    }

    /**
     * 断言value <= expect，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void elt(int value, int expect, int errcode, final String message) {
        if (value > expect) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言value <= expect，否则调用{@link #fail(int)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void elt(long value, long expect, int errcode) {
        elt(value, expect, errcode, null);
    }

    /**
     * 断言value <= expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void elt(long value, long expect, int errcode, String format, Object... args) {
        if (value > expect) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }

    /**
     * 断言value <= expect，否则调用{#fail(int, String)}抛出异常.
     *
     * @param expect
     *            断言的值
     * @param value
     *            待测试的值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void elt(long value, long expect, int errcode, final String message) {
        if (value > expect) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言start <= value <= end(左右都为闭区间)，否则调用{#fail(int, String)}抛出异常.
     *
     * @param value
     *            待测试的值
     * @param start
     *            起始值
     * @param end
     *            结束值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static void between(int value, int start, int end, int errcode) {
        between(value, start, end, errcode, null);
    }

    /**
     * 断言start <= value <= end(左右都为闭区间)，否则调用{ #fail(int, String)}抛出异常.
     *
     * @param value
     *            待测试的值
     * @param start
     *            起始值
     * @param end
     *            结束值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param format
     *            格式化字符串，用来生成异常信息
     * @param args
     *            格式化字符串的参数
     */
    public static void between(int value, int start, int end, int errcode, String format, Object... args) {
        if (!((value >= start) && (value <= end))) {
//            fail(errcode, String.format(format, args));
            fail(errcode, format,args);
        }
    }


    /**
     * 断言start <= value <= end(左右都为闭区间)，否则调用{#fail(int, String)}抛出异常.
     *
     * @param value
     *            待测试的值
     * @param start
     *            起始值
     * @param end
     *            结束值
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static void between(int value, int start, int end, int errcode, final String message) {
        if (!((value >= start) && (value <= end))) {
            fail(errcode, message);
        }
    }*/

    /**
     * 断言item包含在collection，否则调用{@link #fail(int)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param collection
     *            要测试容器
     * @param value
     *            要测试的对象
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     */
    public static <T> void in(T value, Collection<T> collection, int errcode) {
        in(value, collection, errcode, null);
    }

    /**
     * 断言item包含在collection, 否则调用{ #fail(int, String)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param collection
     *            要测试容器
     * @param value
     *            要测试的对象
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
//     * @param message
     *            失败时抛出的ServiceError异常的错误消息
     */
    /*public static <T> void in(T value, Collection<T> collection, int errcode, final String message) {
        notNull(collection, errcode, message);
        if (!collection.contains(value)) {
            fail(errcode, message);
        }
    }*/

    public static <T> void in(T value, Collection<T> collection, int errcode, String format, Object... args) {
        notNull(collection, errcode, format,args);
        if (!collection.contains(value)) {
            fail(errcode, format,args);
        }
    }

    /**
     * 断言item包含在给定的可变参数列表中, 否则调用{@link #fail(int)}抛出异常.
     *
     * @param <T>
     *            变量类型
     * @param value
     *            要测试的对象
     * @param errcode
     *            失败时抛出的ServiceError异常的错误码
     * @param args
     *            格式化字符串的参数
     */
    public static <T> void in(T value, int errcode, T... args) {
        notNull(value, errcode);
        gt(args.length, 0, errcode);
        for (T s : args) {
            if (value.equals(s)) {
                return;
            }
        }
        fail(errcode);
    }

}
