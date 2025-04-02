package com.itheima.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommonImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        return new String[]{"com.itheima.springbootbeanregister.config.CommonConfig"};
        List<String> imports = new ArrayList<>();
        InputStream is = CommonImportSelector.class.getClassLoader().getResourceAsStream("common.imports");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            while((line = br.readLine())!=null){
                imports.add(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        return imports.toArray(new String[0]); // 返回 集合转换成字符串的数组
    }
}
