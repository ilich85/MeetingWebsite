package com.ilich.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class AppContext {
    static ClassPathXmlApplicationContext getContext() {
        return new ClassPathXmlApplicationContext("app-context.xml");
    }
}