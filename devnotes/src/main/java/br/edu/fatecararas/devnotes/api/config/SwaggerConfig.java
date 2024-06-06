// package br.edu.fatecararas.devnotes.api.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Component;

// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// // @EnableSwagger2
// // @Component
// public class SwaggerConfig {
//     @Bean
//     public Docket docket() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .select()
//                 .apis(RequestHandlerSelectors.basePackage("br.edu.fatecararas.devnotes.api.resources"))
//                 .paths(PathSelectors.any())
//                 .build()
//                 .apiInfo(apiInfo());
//     }

//     private ApiInfo apiInfo(){
//         return new ApiInfoBuilder()
//                     .title("DevNotes API")
//                     .description("API para compartilhamento de conhecimento entre DEVs.")
//                     .version("1.0")
//                     .contact(contact())
//                     .build();
//     }

//     private Contact contact(){
//         return new Contact("Esdras B Silva",
//                 "http://github.com/f290DSM/devnotes",
//                 "bzr4@icloud.com");
//     }


// }
