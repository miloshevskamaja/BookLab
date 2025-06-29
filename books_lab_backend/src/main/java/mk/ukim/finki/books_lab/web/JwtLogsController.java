//package mk.ukim.finki.books_lab.web;
//
//import mk.ukim.finki.books_lab.dto.DisplayBookDto;
//import mk.ukim.finki.books_lab.dto.DisplayJwtLogDto;
//import mk.ukim.finki.books_lab.service.application.JwtLogsApplicationService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/jwt-logs")
//public class JwtLogsController {
//    private final JwtLogsApplicationService jwtLogsApplicationService;
//
//    public JwtLogsController(JwtLogsApplicationService jwtLogsApplicationService) {
//        this.jwtLogsApplicationService = jwtLogsApplicationService;
//    }
//    @GetMapping()
//    public List<DisplayJwtLogDto> findAll(){
//        return jwtLogsApplicationService.findAll();
//    }
//}
