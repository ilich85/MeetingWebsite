package com.ilich.controller;

import com.ilich.model.SearchRequest;
import com.ilich.model.UserInfo;
import com.ilich.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestScope
@RequestMapping("/search")
public class SearchController {
    private final UserFromCookies userFromCookies = new UserFromCookies();
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchPage(@ModelAttribute("searchRequest") SearchRequest sr, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int idUser = userFromCookies.getCurrentUserId(request.getCookies());
        if (idUser == 0) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        final Map<String, String> genders = new HashMap<>();
        genders.put("", "");
        genders.put("Man", "Мужчину");
        genders.put("Woman", "Девушку");
        modelAndView.setViewName("searchPage");
        modelAndView.addObject("genders", genders);
        modelAndView.addObject("searchRequest", sr);
        if ((sr.getCity() == null || sr.getCity().equals("")) && (sr.getGender() == null || sr.getGender().equals(""))
                && sr.getMinAge() == null && sr.getMaxAge() == null) {
            return modelAndView;
        } else {
            List<UserInfo> users = searchUsers(sr, idUser);
            modelAndView.addObject("searchResult", users);
            return modelAndView;
        }
    }

    private List<UserInfo> searchUsers(SearchRequest sr, int idUser) {
        List<Object[]> searchResult = new ArrayList<>();
        List<UserInfo> users = new ArrayList<>();
        if (!sr.getCity().equals("") && !sr.getGender().equals("")
                && sr.getMinAge() != null && sr.getMaxAge() != null) {
            searchResult = searchService.getUsersByAllFields(sr);
        } else if (sr.getCity().equals("") && sr.getGender().equals("") && sr.getMinAge() == null) {
            searchResult = searchService.getUsersByMaxAge(sr.getMaxAge());
        } else if (sr.getCity().equals("") && sr.getGender().equals("") && sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByMinAge(sr.getMinAge());
        } else if (sr.getCity().equals("") && sr.getMinAge() == null && sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByGender(sr.getGender());
        } else if (sr.getGender().equals("") && sr.getMinAge() == null && sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByCity(sr.getCity());
        } else if (sr.getCity().equals("") && sr.getGender().equals("")) {
            searchResult = searchService.getUsersByAge(sr.getMinAge(), sr.getMaxAge());
        } else if (sr.getMinAge() == null && sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByCityAndGender(sr.getCity(), sr.getGender());
        } else if (sr.getCity().equals("") && sr.getMinAge() == null) {
            searchResult = searchService.getUsersByMaxAgeAndGender(sr.getMaxAge(), sr.getGender());
        } else if (sr.getGender().equals("") && sr.getMinAge() == null) {
            searchResult = searchService.getUsersByMaxAgeAndCity(sr.getMaxAge(), sr.getCity());
        } else if (sr.getCity().equals("") && sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByMinAgeAndGender(sr.getMinAge(), sr.getGender());
        } else if (sr.getGender().equals("") && sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByMinAgeAndCity(sr.getMinAge(), sr.getCity());
        } else if (sr.getCity().equals("")) {
            searchResult = searchService.getUsersByAgeAndGender(sr);
        } else if (sr.getGender().equals("")) {
            searchResult = searchService.getUsersByAgeAndCity(sr);
        } else if (sr.getMinAge() == null) {
            searchResult = searchService.getUsersByMaxAgeAndGenderAndCity(sr);
        } else if (sr.getMaxAge() == null) {
            searchResult = searchService.getUsersByMinAgeAndGenderAndCity(sr);
        }
        for (Object[] info : searchResult) {
            if ((Integer) info[0] != idUser) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId((Integer) info[0]);
                userInfo.setName((String) info[1]);
                userInfo.setCity((String) info[2]);
                userInfo.setAge((Integer) info[3]);
                userInfo.setMainPhotoId((Long) info[4]);
                users.add(userInfo);
            }
        }
        return users;
    }
}