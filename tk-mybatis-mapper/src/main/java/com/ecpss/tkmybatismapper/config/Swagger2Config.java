package com.ecpss.tkmybatismapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
//@ComponentScan(basePackages = "com.ecpss.tkmybatismapper.controller")
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.ecpss.tkmybatismapper.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact=new Contact("","","");
        return new ApiInfoBuilder()
                //页面标题
                .title("农业")
                //描述
                .description("描述")
                .termsOfServiceUrl("http://localhost:8082/swagger-ui.html")
                //创建人
                .contact(contact)
                //版本
                .version("1.0")
                .build();
    }
}
