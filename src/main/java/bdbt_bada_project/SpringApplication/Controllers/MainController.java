package bdbt_bada_project.SpringApplication.Controllers;
import bdbt_bada_project.SpringApplication.Entities.Pracownicy;
import bdbt_bada_project.SpringApplication.Entities.PracownicyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PracownicyDAO dao;

    @RequestMapping("/main")
    public String defaultAfterLogin
            (HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/main_admin";
        }
        else if (request.isUserInRole("USER")) {
            return "redirect:/main_user";
        }
        else {
            return "redirect:/index";
        }
    }

    @RequestMapping("/main_admin")
    public String showUserData(Model model, HttpServletRequest request) {
        List<Pracownicy> pracownicyList = dao.list();
        model.addAttribute("pracownicyList", pracownicyList);

        if (request.isUserInRole("ADMIN")) {
            return "admin/main_admin";
        } else {
            return "redirect:/main_user";
        }
    }
}