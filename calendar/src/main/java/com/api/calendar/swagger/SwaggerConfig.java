package com.api.calendar.swagger;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .paths(PathSelectors.any())
            .apis(withClassAnnotation(RestController.class))
            .build()
            .apiInfo(internalApiInfo())
            .globalResponseMessage(RequestMethod.GET, responseMessage())
            .globalResponseMessage(RequestMethod.POST, responseMessage())
            .globalResponseMessage(RequestMethod.PUT, responseMessage())
            .globalResponseMessage(RequestMethod.DELETE, responseMessage());
    }

    private ApiInfo internalApiInfo() {
        String title = "Calendar Interview API";
        String description =
            "<p>Calendar Interview API Documentation</p>" +
                "<p>This API schedule 1 hour slot interviews :</p>" +
                "<br/><p>Rules : </p>" +
                "<p> This api provides to the interviewers and candidates to schedule an interview </p>" +
                "<p> An Interviewer can schedule on their calendar multiple timeslots per day.</p>" +
                "<p> An interview slot is a 1-hour period of time that spreads from the beginning of any " +
                "hour until the beginning of the next hour. For example, a time span between 9am and " +
                "10am is a valid interview slot, whereas between 9:30am and 10:30am it is not.</p>" +
                "<p> Schedule interview steps: </p>" +
                "<p> We have two interviewers, one interviewer schedule on Monday from 9AM to 11AM " +
                "this will create 2 timeslots for interviews, 9am-10am and 10am-11am. " +
                "And user Ingrid will schedule on Monday  from 8:30AM to 12:30 this will create 3 timeslots " +
                "for interviews, 9am-10am and 10am-11am and 11am-12am. </p>" +
                "<p> The candidate will schedule the interview on the slot 9am-10am or 10am-11am. </p>" +
                "<p>Other Info : </p>" +
                "<p>1 - Local Date and Time (Starting and Ending) format is : LocalDateTime </p>" +
                "<p>2 - Available Candidate Users Logins : candidate</p>" +
                "<p>3 - Available Interview Users Logins : ines, ingrid</p>" +
                "<p>4 - If you need to register a new user please contact our support team.</p>" +
                "<p>5 - You will find more information in each Dto/Endpoint documentation.</p>";
        String version = "0.0.1";
        Contact contact = new Contact("Challenge", "https://www.meuteste.com/contact-us", "meuteste@teste.com");

        return new ApiInfo(title, description, version, null, contact, null, null,
            Collections.emptyList());
    }


    private List<ResponseMessage> responseMessage() {

        ResponseMessage internalServerError = new ResponseMessageBuilder()
            .code(500)
            .message("Internal server error")
            .build();

        ResponseMessage forbidden = new ResponseMessageBuilder()
            .code(403)
            .message("Forbidden")
            .build();

        ResponseMessage unauthorised = new ResponseMessageBuilder()
            .code(401)
            .message("Unauthorised")
            .build();

        ResponseMessage notFound = new ResponseMessageBuilder()
            .code(404)
            .message("Not found")
            .build();

        ResponseMessage success = new ResponseMessageBuilder()
            .code(200)
            .message("Success")
            .build();

        ResponseMessage invalidRequestBody = new ResponseMessageBuilder()
            .code(422)
            .message("Invalid request body")
            .build();

        return List.of(
            internalServerError,
            forbidden,
            unauthorised,
            notFound,
            success,
            invalidRequestBody);

    }

}
