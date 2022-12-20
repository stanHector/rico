package service.ricotunes.giftcards.config;//package ricotunes.services.card.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///*created by Hector Developers
//6-30-2022
//*/
//@Configuration
//@EnableSwagger2
//public class ApplicationConfig {
//
////    @Bean
////    public Docket productApi() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.basePackage("http://localhost:8080/api/v1/swagger-ui.html"))
////                .build()
////                .pathMapping("/");
////    }
//
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("http://localhost:8080/api/v1/swagger-ui.html"))
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/api/v1/swagger-ui.html");
//    }
//
//    private ApiInfo getApiInfo() {
//        Contact contact = new Contact("Admin", "http://localhost:8080/api/v1/swagger-ui.html", "admins@rico.org");
//        return new ApiInfoBuilder()
//                .title("Rico Tunez API")
//                .description("Documentation User profile api")
//                .version("1.0.0")
//                .license("Apache 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//                .contact(contact)
//                .build();
//    }
//}