package service.ricotunes.giftcards.controller;//package ricotunes.services.card.controller;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///*created by Hector Developers
//6-30-2022
//*/
//@CrossOrigin(origins = "http://localhost:3001")
//@RestController
//public class DefaultController implements ErrorController {
//
//    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
//    @ApiOperation(value = "Link to swagger", notes = "Goto http://localhost:8080/api/v1/swagger-ui.html", response = String.class)
//    public String handleError() {
//        return "http://localhost:8080/api/v1/swagger-ui.html";
//    }
//
////    @Override
////    public String getErrorPath() {
////        return "/error";
////    }
//}