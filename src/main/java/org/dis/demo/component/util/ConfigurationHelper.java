package org.dis.demo.component.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.dis.demo.component.annotation.ComponentParam;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class ConfigurationHelper {

    private final static String OPTION_SEP = "$";

    /**
     * 自动生成组件配置文件
     * @param componentName
     * @param endpointClazz
     */
    public static void generateConfiguraion(String componentName, Class endpointClazz) {
        InputStream ins = null;
        FileWriter writer = null;
        try {
            Map<String, Object> param = parseComponentParam(componentName, endpointClazz);
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.init();
            VelocityContext context = new VelocityContext();
            context.put("param", param);
            ins = ConfigurationHelper.class.getResourceAsStream("/template.yaml");
            File file = createOutputFile(componentName);
            writer = new FileWriter(file);
            velocityEngine.evaluate(context, writer, "nodeConfig", IOUtils.toString(ins));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ins);
            IOUtils.closeQuietly(writer);
        }

    }

    private static Map<String, Object> parseComponentParam(String componentName, Class endpointClazz) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("componentName", componentName);
        Field[] fields = endpointClazz.getDeclaredFields();
        List properties = new ArrayList();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ComponentParam.class)) {
                ComponentParam componentParam = field.getAnnotation(ComponentParam.class);
                Map element = new HashMap();
                properties.add(element);
                element.put("id", field.getName());
                element.put("title", componentParam.title_en());
                element.put("title_cn", componentParam.title_cn());
                element.put("type", parseType(field));
                element.put("formElement", componentParam.formElement());
                if (componentParam.selectItems() != null) {
                    element.put("selectItems", parseEnumItems(componentParam));
                }
                element.put("defaultValue", componentParam.defaultValue());
            }
        }
        paramMap.put("properties", properties);
        return paramMap;
    }

    private static List<Map> parseEnumItems(ComponentParam param) {
        List<Map> enumItemList = new ArrayList<Map>();
        Arrays.stream(param.selectItems()).forEach(item -> {
            String[] values = StringUtils.split(item, OPTION_SEP);
            Map enumItem = new HashMap();
            enumItem.put("label", values[0]);
            enumItem.put("value", values[1]);
            enumItemList.add(enumItem);
        });
        return enumItemList;
    }


    private static String parseType(Field field) {
        if (field.getType() == String.class) {
            return "string";
        }
        if (field.getType() == String[].class) {
            return "array";
        }
        if (field.getType() == int.class || field.getType() == double.class || field.getType() == long.class
                || field.getType() == Integer.class || field.getType() == Double.class || field.getType() == Long.class) {
            return "number";
        }
        if (field.getType() == boolean.class || field.getType() == Boolean.class) {
            return "boolean";
        }
        return "string";
    }

    private static File createOutputFile(String componentName) {
        File classFilePath = new File(ConfigurationHelper.class.getResource("/").getPath());
        String filePath = classFilePath.getParentFile().getParentFile().getPath();
        return new File(filePath + File.separator + componentName + ".yaml");
    }

}
