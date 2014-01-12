package org.timesheet.web;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.timesheet.web.helpers.EntityGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-19
 * Time: 上午12:26
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @Resource
    private EntityGenerator entityGenerator;

    @RequestMapping(method = RequestMethod.GET)
    public String showMenu(Model model) {
        model.addAttribute("today", new Date());
        return "index";
    }

    @PostConstruct
    public void prepareFakeDomain() {
        entityGenerator.deleteDomain();
        entityGenerator.generateDomain();
    }
}
