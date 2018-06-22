package com.ilich.controller;

import com.ilich.model.User;
import com.ilich.service.UserInfoService;
import com.ilich.service.UserInfoServiceImpl;
import com.ilich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestScope
public class UserController {

    private final UserFromCookies userFromCookies = new UserFromCookies();
    private final UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public ModelAndView registerPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String s = request.getParameter("check");
        String result = "";
        if (s != null) {
            switch (s) {
                case "exists":
                    result = "Имя пользователя используется";
                    break;
                case "username":
                    result = "Имя пользователя должно быть от 6 до 25 символов";
                    break;
                case "password":
                    result = "Пароль должен быть от 6 до 25 символов";
                    break;
            }
            modelAndView.addObject("check", result);
        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check-user")
    public String checkUser(@RequestParam String username, @RequestParam String password,
                            HttpServletResponse response, final RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String result;
        String checkResult = userService.checkUser(user);
        switch (checkResult) {
            case "wrong password":
                redirectAttributes.addAttribute("check", "pass");
                result = "redirect:/index";
                break;
            case "user not found":
                redirectAttributes.addAttribute("check", "user");
                result = "redirect:/index";
                break;
            default:
                response.addCookie(new Cookie("currentUser", checkResult));
                if (new UserInfoServiceImpl().getInfo(Integer.parseInt(checkResult)) == null) {
                    result = "redirect:/set-user-info";
                } else if (userInfoService.getPhotoById(Integer.parseInt(checkResult)) == null) {
                    result = "redirect:/set-user-photo";
                } else {
                    result = "redirect:/user_" + checkResult;
                }
                break;
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,
                               final RedirectAttributes redirectAttributes) {
        String result = "redirect:/registration";
        String check;
        if (username.length() < 6 || username.length() > 25) {
            check = "username";
        } else if (password.length() < 6 || password.length() > 25) {
            check = "password";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            String checkResult = userService.register(user);
            switch (checkResult) {
                case "user exists":
                    check = "exists";
                    break;
                default:
                    check = "complete";
                    result = "redirect:/index";
                    break;
            }
        }
        redirectAttributes.addAttribute("check", check);
        return result;
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, @RequestParam String oldPass,
                                 @RequestParam String newPass, @RequestParam String confirmPass,
                                 final RedirectAttributes redirectAttributes) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        String result;
        String userPass = userService.getPasswordById(idUser);
        if (oldPass.equals(userPass)) {
            if (newPass.length() > 6 && newPass.length() < 25) {
                if (newPass.equals(confirmPass)) {
                    userService.changePassword(newPass, idUser);
                    result = "success";
                } else {
                    result = "mismatch";
                }
            } else {
                result = "wrong";
            }
        } else {
            result = "pass";
        }
        redirectAttributes.addAttribute("update-pass", result);
        return "redirect:/update-info";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        int idUser = getIdUser(cookies);
        if (idUser == 0) {
            return "redirect:/login";
        } else {
            userFromCookies.removeCookie(cookies);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/remove-user", method = RequestMethod.POST)
    public String removeUser(HttpServletRequest request) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/login";
        } else {
            userService.removeAccount(idUser);
            return "redirect:/index";
        }
    }

    private int getIdUser(Cookie[] cookies) {
        return userFromCookies.getCurrentUserId(cookies);
    }
}