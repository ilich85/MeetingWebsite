package com.ilich.controller;

import com.ilich.model.Photo;
import com.ilich.service.PhotoService;
import com.ilich.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestScope
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private UserInfoService userInfoService;
    private final UserFromCookies userFromCookies = new UserFromCookies();

    @ResponseBody
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    public byte[] renderPhoto(@PathVariable Long id) throws IOException {
        return photoService.getPhoto(id).getPhoto();
    }

    @RequestMapping(value = "/set-user-photo", method = RequestMethod.GET)
    public ModelAndView userPhotoForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (getIdUser(request.getCookies()) == 0) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("setUserPhoto");
        modelAndView.addObject("checkPhoto", request.getParameter("checkPhoto"));
        return modelAndView;
    }

    @RequestMapping(value = "/set-user-photo", method = RequestMethod.POST)
    public String setUserPhoto(@RequestParam MultipartFile file, HttpServletRequest request,
                               final RedirectAttributes ra) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        if (file.getSize() == 0) {
            ra.addAttribute("checkPhoto", "Файл не выбран");
            return "redirect:/set-user-photo";
        } else {
            Photo photo = new Photo();
            photo.setUserId(getIdUser(request.getCookies()));
            try {
                photo.setPhoto(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoService.addPhoto(photo);
            userInfoService.setMainPhoto(photo.getId(), idUser);
            return "redirect:/user_" + idUser;
        }
    }

    @RequestMapping(value = "/set-main-photo/{id}", method = RequestMethod.POST)
    public String setMainPhoto(HttpServletRequest request, @PathVariable Long id) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        userInfoService.setMainPhoto(id, idUser);
        return "redirect:/user_" + idUser + "/photos";
    }

    @RequestMapping(value = "/remove-photo/{id}", method = RequestMethod.POST)
    public String removePhoto(HttpServletRequest request, @PathVariable Long id) {
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        photoService.removePhoto(id);
        return "redirect:/user_" + idUser + "/photos";
    }

    @RequestMapping(value = "/add-user-photo", method = RequestMethod.POST)
    public String addUserPhoto(@RequestParam MultipartFile file, HttpServletRequest request,
                               final RedirectAttributes ra) throws IOException {
        if (file.getSize() == 0) {
            ra.addAttribute("update-info", "no photo");
            return "redirect:/update-info";
        } else {
            int idUser = getIdUser(request.getCookies());
            Photo photo = new Photo();
            photo.setPhoto(file.getBytes());
            photo.setUserId(idUser);
            photoService.addPhoto(photo);
            userInfoService.setMainPhoto(photo.getId(), idUser);
            return "redirect:/update-info";
        }
    }

    @RequestMapping(value = "/user_{id}/photos", method = RequestMethod.GET)
    public ModelAndView userPhotos(@PathVariable int id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int idUser = getIdUser(request.getCookies());
        if (idUser == 0) {
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("photos", photoService.getPhotosByUserId(id));
            modelAndView.addObject("currentUser", idUser);
            modelAndView.addObject("userId", id);
            modelAndView.setViewName("photos");
        }
        return modelAndView;
    }

    private int getIdUser(Cookie[] cookies) {
        return userFromCookies.getCurrentUserId(cookies);
    }
}