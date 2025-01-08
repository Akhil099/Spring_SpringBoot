package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class SayHelloController {
    //"say-hello" => return this text back when we are clicking with link(/say-hello) "Hello! what are you doing today?"

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        Date date = new Date();
        String today = date.toGMTString();
        return "Hello! What are you learning today on"+today+"?"; //The browser will look for a view which we can provide using annotation
        //@ResponseBody , which will display as it is what is returned by the method
    }


    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHTML() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My first HTML Page-Changed</title>");
        sb.append("</head>");
        sb.append("<body> My first HTML Page-Changed </body>");
        sb.append("</html>");

        return sb.toString();
    }


    //src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    @RequestMapping("say-hello-jsp")
    public String sayHelloJSP() {
        return "sayHello";
    }

}
