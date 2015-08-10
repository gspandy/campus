package org.campus.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.campus.core.exception.CampusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

public abstract class ConfigurationLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    public static Resource getConfigFile(String fileName) {
        ResourceLoader loader = new DefaultResourceLoader();
        return loader.getResource("file:" + "/files/" + fileName);
    }

    public static List<String> getMessageFilePathList() {
        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = loader.getResources("file:" + "/i18n/" + "*.properties");
        } catch (IOException e) {
            throw new CampusException("获取message文件列表失败", e);
        }
        List<String> filePathList = new ArrayList<String>(resources.length);
        for (Resource messageFile : resources) {
            String uri = null;
            try {
                uri = messageFile.getURI().toString();
            } catch (IOException e) {
                throw new CampusException("获取message文件URI失败", e);
            }
            filePathList.add(trimFileExtension(uri));
        }
        return filePathList;
    }

    private static String trimFileExtension(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("."));
    }

    public static Properties getConfigProperties() throws IOException {
        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        Resource[] resources = loader.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/*.properties");
        Properties props = new Properties();
        loadProperties(resources, props);
        return props;
    }

    private static void loadProperties(Resource[] resources, Properties props) throws IOException {
        for (Resource resource : resources) {
            logger.info("Loading properties file from {}", resource);
            try {
                PropertiesLoaderUtils.fillProperties(props, resource);
            } catch (IOException e) {
                logger.warn("Could not load properties from {}: " + e.getMessage(), resource);
                throw e;
            }
        }
    }
}
