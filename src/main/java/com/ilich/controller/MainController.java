package com.ilich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestScope
public class MainController {

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/index", "/add-message",
            "/register", "/remove-user", "/send-message", "/change-password", "/add-user-photo",
            "/add-comment/{id}", "/add-user-photo", "/set-main-photo/{id}", "/remove-photo/{id}"})
    public ModelAndView loginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String s = request.getParameter("check");
        if (s != null) {
            String result = "";
            switch (s) {
                case "pass":
                    result = "Неправильный пароль";
                    break;
                case "user":
                    result = "Неправильный логин";
                    break;
                case "complete":
                    result = "Вы успешно зарегистрировались";
                    break;
            }
            modelAndView.addObject("checkResult", result);
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }
}