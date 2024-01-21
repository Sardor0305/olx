package uz.pdp.olx.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;


@Configuration
public class MailConfig {

    @Bean
    @Primary
    public FreeMarkerConfigurationFactory configurationFactory() {
        var factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPath("classpath:/templates");
        return factory;
    }

}
