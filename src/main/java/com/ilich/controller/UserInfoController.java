package com.ilich.controller;

import com.ilich.model.Characteristic;
import com.ilich.model.Message;
import com.ilich.model.UserInfo;
import com.ilich.service.CharacteristicService;
import com.ilich.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestScope
@RequestMapping
public class UserInfoController {

    private final UserFromCookies userFromCookies = new UserFromCookies();

    private final UserInfoService userInfoService;
    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(value = "/set-user-info", method = RequestMethod.GET)
    public ModelAndView userInfo(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("setUserInfo");
        final Map<String, String> genders = new HashMap<>();
        genders.put("Man", "Мужчина");
        genders.put("Woman", "Женщина");
        modelAndView.addObject("genders", genders);
        modelAndView.addObject("userInfo", new UserInfo());
        return modelAndView;
    }

    @RequestMapping(value = "/set-user-info", method = RequestMethod.POST)
    public String setUserInfo(@ModelAttribute UserInfo info, HttpServletRequest request) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        info.setUserId(idUser);
        userInfoService.setInfo(info);
        return "redirect:/set-user-photo";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_{id}")
    public ModelAndView userInfoById(@PathVariable int id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Characteristic characteristic = characteristicService.getCharacteristic(id);
        UserInfo userInfo = userInfoService.getInfo(id);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("message", new Message());
        if (characteristic != null) {
            modelAndView.addObject("characteristic", characteristic);
        }
        modelAndView.addObject("currentUser", idUser);
        modelAndView.setViewName("userPageById");
        return modelAndView;
    }

    @RequestMapping(value = "/user-profile", method = RequestMethod.GET)
    public String userProfile(HttpServletRequest request) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        return "redirect:/user_" + idUser;
    }

    private int getIdUser(Cookie[] cookies) {
        return userFromCookies.getCurrentUserId(cookies);
    }
}