package com.example.rpc.util.annotationImpl;import com.example.rpc.util.annotation.EnableRPC;import com.example.rpc.util.remoteFacadeScan.RemoteFacadeBeanDefinitionScanner;import org.springframework.beans.factory.support.*;import org.springframework.context.EnvironmentAware;import org.springframework.context.ResourceLoaderAware;import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;import org.springframework.core.annotation.AnnotationAttributes;import org.springframework.core.env.Environment;import org.springframework.core.io.ResourceLoader;import org.springframework.core.io.support.PathMatchingResourcePatternResolver;import org.springframework.core.io.support.ResourcePatternResolver;import org.springframework.core.io.support.ResourcePatternUtils;import org.springframework.core.type.AnnotationMetadata;import org.springframework.core.type.classreading.CachingMetadataReaderFactory;import org.springframework.core.type.classreading.MetadataReaderFactory;import java.io.IOException;public class EnableRPCImpl implements ImportBeanDefinitionRegistrar, EnvironmentAware, ResourceLoaderAware {    private Environment environment;    private ResourceLoader resourceLoader;    static final String resourcePattern = "**/*.class";    private ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);    @Override    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {        try {            //new doscan            enableRPC(metadata, registry);        } catch (IOException e) {            e.printStackTrace();        }    }    private void enableRPC(AnnotationMetadata metadata, BeanDefinitionRegistry registry) throws IOException {        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableRPC.class.getName()));        String[] basePackages = annotationAttributes.getStringArray("basePackage");        RemoteFacadeBeanDefinitionScanner scanner = new RemoteFacadeBeanDefinitionScanner(false, environment, metadataReaderFactory, resourcePatternResolver, registry, metadata);        scanner.scan(basePackages);    }    private ResourcePatternResolver getResourcePatternResolver() {        if (this.resourcePatternResolver == null) {            this.resourcePatternResolver = new PathMatchingResourcePatternResolver();        }        return this.resourcePatternResolver;    }    @Override    public void setEnvironment(Environment environment) {        this.environment = environment;    }    public Environment getEnvironment() {        return environment;    }    @Override    public void setResourceLoader(ResourceLoader resourceLoader) {        this.resourceLoader = resourceLoader;    }}