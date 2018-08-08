package com.ls.plugins.support;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
public class Swagger2 {




    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("travel")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                //.globalResponseMessage(RequestMethod.GET,customerResponseMessage())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ls.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
                .termsOfServiceUrl("http://blog.csdn.net/saytime")
                .version("1.0")
                .build();
    }

//    private List<ResponseMessage> customerResponseMessage(){
//        return ListUtils.convertToList(
//                new ResponseMessageBuilder()//500
//                        .code(CodeEnum.error.getValue())
//                        .message(CodeEnum.error.getDescription())
//                        .responseModel(new ModelRef("Error"))
//                        .build(),
//                new ResponseMessageBuilder()//401
//                        .code(CodeEnum.paramError.getValue())
//                        .message(CodeEnum.paramError.getDescription())
//                        .build());
//    }

}
