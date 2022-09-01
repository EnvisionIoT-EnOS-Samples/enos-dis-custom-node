package org.dis.demo.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务自定义组件的参数注解
 * 目前仅支持String和boolean类型数据
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentParam {

    /**
     * 字段名称(中文)
     */
    String title_cn() default "";

    /**
     * 字段名称(英语)
     */
    String title_en();

    /**
     * ui类型
     * Input | Select | Switch | Checkbox | TextArea | InputNumber | Password | RangePicker | TimePicker
     *
     * @return
     */
    String formElement();

    /**
     * 默认值
     */
    String defaultValue();


    /**
     * select元素
     * 格式: {"OPTION1$1", "OPTION2$2", "OPTION3$3"})
     * lable和value用$     *
     * @return
     */
    String[] selectItems() default {};

    /**
     * 是否必填
     * @return
     */
    boolean required() default false;

}
