package com.fxx.config.componetScan;

import com.fxx.dto.Color;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class ColorBeanLoadFilter implements TypeFilter {

    @Override
    public boolean match (MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
            throws IOException {
        // 当前被扫描类的注解信息
        @SuppressWarnings("unused")
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 当前被扫描类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 当前被扫描类资源信息
        @SuppressWarnings("unused")
        Resource resource = metadataReader.getResource();
        try {
            String className = classMetadata.getClassName();
            Class<?> forName = Class.forName(className);
            if (Color.class.isAssignableFrom(forName)) {
                // 如果是Color的子类，就加载到IOC容器
                return true;
            }
        } catch (ClassNotFoundException e) {
//			e.printStackTrace();
        }
        return false;
    }

}
