package com.hllwz.stellartownserver.common;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;

/**
 * Swagger配置类
 * @author Linorman
 * @version 1.0.0
 */
@Deprecated
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket userGroup(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("userApis")
                .select()
                //只显示admin下面的路径
                .paths(Predicates.and(PathSelectors.regex("/api/v1/.*")))
                .build();
    }

    @Bean
    public Docket postGroup() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("forumApis")
                .select()
                //只显示admin下面的路径
                .paths(Predicates.and(PathSelectors.regex("/api/v1/forum/.*")))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("StellarTown--api文档")
                .description("StellarTown程序后端接口定义")
                .version("1.0")
                .contact(new Contact("Linorman","","3033797357@qq.com"))
                .build();
    }
}
