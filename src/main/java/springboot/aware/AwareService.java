package springboot.aware;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {
    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    public void outputResult() {
        String absolutePath = new File("").getAbsolutePath();
        System.out.println(absolutePath);
        System.out.println("Bean's name:" + beanName);
        FileSystemResource fileSystemResource = new FileSystemResource("./src/main/java/springboot/aware/Hello.txt");
        try {
            OutputStream outputStream = fileSystemResource.getOutputStream();
            outputStream.write("Hello World!".getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Resource resource = loader.getResource("classpath:text/Hello.txt");
        try {
            System.out.println("Resource loaded file content: " + Arrays.toString(IOUtils.toByteArray(resource.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
