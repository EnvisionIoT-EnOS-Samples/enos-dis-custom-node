package org.dis.demo.process;

import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.dis.demo.component.OrchCustomNodeComponent;
import org.dis.demo.component.OrchCustomNodeEndpoint;
import org.dis.demo.component.annotation.ComponentParam;
import org.dis.demo.component.util.ConfigurationHelper;

/**
 * Define component param here.
 */
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "custom-node", title = "CustomNode", syntax = "custom-node:name",
        label = "custom")
public class CustomNodeEndpoint extends OrchCustomNodeEndpoint {

    @UriPath(description = "pathValue")
    @Metadata(required = true)
    @ComponentParam(title_cn = "路径", title_en = "Path Value", formElement = "Input", defaultValue = "foo", required = true)
    private String pathValue;

    @UriParam(description = "input")
    @ComponentParam(title_cn = "输入", title_en = "Input", formElement = "Input", defaultValue = "", required = true)
    private String input;

    @UriParam(description = "select")
    @ComponentParam(title_cn = "选择值", title_en = "Select", formElement = "Select", defaultValue = "1", selectItems = {"value1$1", "value2$2"}, required = true)
    private Double select;

    @UriParam(description = "inputNumber")
    @ComponentParam(title_cn = "数字", title_en = "InputNumber", formElement = "InputNumber", defaultValue = "0", required = true)
    private Double inputNumber;

    @UriParam(description = "password")
    @ComponentParam(title_cn = "密码", title_en = "Password", formElement = "Password", defaultValue = "", required = true)
    private String password;

    public CustomNodeEndpoint(String uri, OrchCustomNodeComponent component) {
        super(uri, component);
    }

    public String getPathValue() {
        return pathValue;
    }

    public void setPathValue(String pathValue) {
        this.pathValue = pathValue;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Double getSelect() {
        return select;
    }

    public void setSelect(Double select) {
        this.select = select;
    }

    public Double getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(Double inputNumber) {
        this.inputNumber = inputNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        ConfigurationHelper.generateConfiguraion("custom-node", CustomNodeEndpoint.class);
    }

}
