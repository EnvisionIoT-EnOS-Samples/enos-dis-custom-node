package org.dis.demo.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author huanrong.zhu
 * @date 2021/4/23
 */
@Data
@Builder
public class Params {
    private String input;
    private Double select;
    private Boolean Switch;
    private Boolean checkbox;
    private Boolean checkbox2;
    private String textarea;
    private Double inputNumber;
    private Double inputNumber2;
    private String password;
    private List<Object> rangePicker;
    private String timePicker;
    private String routeUri;
}