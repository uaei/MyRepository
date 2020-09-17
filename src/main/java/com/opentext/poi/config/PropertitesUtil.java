package com.opentext.poi.config;

import org.springframework.core.io.ClassPathResource;
import java.io.*;
import java.util.Properties;

public class PropertitesUtil {

    public static  Properties props;

    static {
        try {
            readPropertiesFile("/model.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties readPropertiesFile(String filePath) throws FileNotFoundException, IOException {
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            InputStream inputStream =classPathResource.getInputStream();
            props = new Properties();
            props.load(new InputStreamReader(inputStream, "UTF-8"));
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getServiceBeanName(String modelName){
        String modelId = props.getProperty(modelName);
        return props.getProperty(modelId);
    }

    public static Long getModelId(String modelName){

        return Long.parseLong(props.getProperty(modelName));
    }

}
