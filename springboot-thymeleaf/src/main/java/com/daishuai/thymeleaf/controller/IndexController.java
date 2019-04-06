package com.daishuai.thymeleaf.controller;

import com.daishuai.thymeleaf.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/5/13 22:12
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Controller
public class IndexController {


    @GetMapping("/index")
    public String index(Model model) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("zhangsan","China",12,1,new Date()));
        persons.add(new Person("sili","English",23,0,new Date()));
        persons.add(new Person("王五","The UK",26,0,new Date()));
        persons.add(new Person("赵六","Canada",9,1,new Date()));
        persons.add(new Person("Tom","China",12,1,new Date()));
        model.addAttribute("persons",persons);
        return "index";
    }

}
