package com.ssafy.happyhouse.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 		//스웨거를 위한 설정 
public class SwaggerConfig {
	private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

	@Bean
	public Docket api() {
		logger.debug("swagger ready........{}", "준비중");
		return new Docket(DocumentationType.SWAGGER_2)
				   .groupName("book") 		//service할 project 이름 
				   .apiInfo(info())
				   .select()
				   .apis(RequestHandlerSelectors.basePackage("com.ssafy.book.controller"))
				    // 서비스할 경로 phone으로 통일  서비스 구분은 요청 method로 구분  
				   .paths(PathSelectors.ant("/book/**")) 
//				    //서비스할 경로를 통일하지 않고 경로명으로 구분해서 사용 
//				   .paths(PathSelectors.any()) 	 		  
				   .build();
	}

	/** 프로젝트의 정보를 만들어 주는 함수 */
	private ApiInfo info() {
		return new ApiInfoBuilder().title("Book Management API")
				.description("핸드폰 관리를 위한 <b>CRUD</b>")
				.license("그냥 쓰세요!!! ")
				.version("3.0")
				.build();
	}
}















