package com.nowcoder.controller;

import com.nowcoder.model.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class indexController {
    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index() {
        return "Hello, dblinux";
    }

    @RequestMapping(value = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "nowcoder") String key) {
        return String.format("GID{%s}, UID{%d}, TYPE{%d}, KEY{%s}", groupId, userId, type, key);
    }

    /**
     * 将变量等数据传到velocity模板中,用于显示在页面上。
     *
     * @param model
     * @return
     */
    @RequestMapping(path = {"/vm"})
    public String news(Model model) {
        model.addAttribute("value1", "vv1");

        // 传递基础数据结构
        List<String> colors = Arrays.asList(new String[]{"RED", "GREEN", "BLUE"});
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 4; ++i) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }

        model.addAttribute("colors", colors);
        model.addAttribute("map", map);

        // 传递自定义业务逻辑
        model.addAttribute("user", new User("Jim"));
        return "news";
    }

    /**
     * 实现get 和 post
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(path = {"/request"})
    @ResponseBody
    public String request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }

        // 常用做法是将sessionId放到cookie中
        // Cookie: JSESSIONID:D973A209E4579DE59ED4344EFA31F17D, 将JavaSessionId放到Cookie中进行传递
        for (Cookie cookie : request.getCookies()) {
            sb.append("Cookie: ");
            sb.append(cookie.getName());
            sb.append(":");
            sb.append(cookie.getValue());
            sb.append("<br>");
        }

        // 添加一些其他常用信息
        sb.append("getMethod: " + request.getMethod() + "<br>");
        sb.append("getPathInfo: " + request.getPathInfo() + "<br>");
        sb.append("getQueryString: " + request.getQueryString() + "<br>");
        sb.append("getRequestURI: " + request.getRequestURI() + "<br>");

        return sb.toString();
    }

    @RequestMapping(path = {"/response"})
    @ResponseBody
    public String reponse(@CookieValue(value = "nowcoderId", defaultValue = "A") String nowcoderId,
                          @RequestParam(value = "key", defaultValue = "key") String key,
                          @RequestParam(value = "value", defaultValue = "value") String value,
                          HttpServletResponse response
    ) {
        response.addCookie(new Cookie(key, value));
        response.addHeader(key, value);
        return "NowCoderId From Cookie: " + nowcoderId;
    }

    /**
     * 实现重定向
     * 代号301:永久转移；
     * 代号302：临时转移
     *
     * @return
     */
    @RequestMapping("/redirect/{code}")
    @ResponseBody
    public RedirectView redirect(@PathVariable("code") int code) {
        RedirectView red = new RedirectView("/", true);
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);        // 看是否是永久转移，不然就是临时转移。
        }
        return red;
    }

    /**
     * 验证管理员密码
     * @param key
     * @return
     */
    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value = "key", required = false) String key){
        if("admin".equals(key)){
            return "hello, My dear";
        }
        throw new IllegalArgumentException("Key Error");
    }

    /**
     * 自定义处理错误信息方法，统一处理错误
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public String error(Exception e){
        return "error: " + e.getMessage();
    }

    /**
     * 1、 IOC: Inverse of Control, 一种思想，将对象与实现分离开，用的时候只需要添加注解，就可以使用实现的方法。Spring实现的优雅。
     *      或者添加配置文件达到相同的效果。
     * 2、 AOP：Aspect Oriented Programming，面向切面编程，所有业务都要处理的业务。
     */
}