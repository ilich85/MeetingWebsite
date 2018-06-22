package com.ilich.controller;

import javax.servlet.http.Cookie;

class UserFromCookies {
    int getCurrentUserId(Cookie[] cookies) {
        int idUser = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentUser")) {
                    idUser = Integer.parseInt(cookie.getValue());
                }
            }
        }
        return idUser;
    }

    void removeCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentUser")) {
                    cookie.setValue("");
                }
            }
        }
    }
}