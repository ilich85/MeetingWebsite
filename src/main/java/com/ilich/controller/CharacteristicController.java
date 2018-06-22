package com.ilich.controller;

import com.ilich.model.Characteristic;
import com.ilich.service.CharacteristicService;
import com.ilich.service.PhotoService;
import com.ilich.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestScope
@RequestMapping(value = "/update-info")
public class CharacteristicController {
    @Autowired
    private CharacteristicService characteristicService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PhotoService photoService;

    private final UserFromCookies userFromCookies = new UserFromCookies();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView updateInfoForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Characteristic c = characteristicService.getCharacteristic(idUser);
        if (c == null) {
            modelAndView.addObject("characteristic", new Characteristic());
        } else {
            modelAndView.addObject("characteristic", c);
        }
        String updateInfo = request.getParameter("update-info");
        if (updateInfo != null) {
            if (updateInfo.equals("success")) {
                modelAndView.addObject("update", "Информация обновлена");
            } else if (updateInfo.equals("no photo")) {
                modelAndView.addObject("update", "Файл не выбран");
            } else if (updateInfo.equals("pass")) {
                modelAndView.addObject("update", "Пароль должен быть от 6 до 25 символов");
            }
        }
        List<Long> userPhotos = photoService.getPhotosByUserId(idUser);
        if (userPhotos != null) {
            modelAndView.addObject("photos", userPhotos);
        }
        String updatePass = request.getParameter("update-pass");
        String result = "";
        if (updatePass != null) {
            switch (updatePass) {
                case "success":
                    result = "Пароль успешно изменен";
                    break;
                case "mismatch":
                    result = "Пароли не совпадают";
                    break;
                case "wrong":
                    result = "Неправильный пароль";
                    break;
            }
            modelAndView.addObject("result", result);
        }
        modelAndView.addObject("userInfo", userInfoService.getInfo(idUser));
        modelAndView.setViewName("updateUserInfo");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateInfo(@ModelAttribute Characteristic characteristic, HttpServletRequest request,
                             final RedirectAttributes redirectAttributes) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        characteristic.setUserId(idUser);
        if (characteristicService.getCharacteristic(idUser) == null) {
            characteristicService.addCharacteristic(characteristic);
        } else {
            characteristicService.updateCharacteristic(characteristic);
        }
        redirectAttributes.addAttribute("update-info", "success");
        return "redirect:/update-info";
    }

    private int getIdUser(Cookie[] cookies) {
        return userFromCookies.getCurrentUserId(cookies);
    }
}