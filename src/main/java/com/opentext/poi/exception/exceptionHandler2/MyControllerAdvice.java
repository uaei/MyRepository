package com.opentext.poi.exception.exceptionHandler2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author GuYaWei
 * @created 2020/9/17 15:28
 * @description： controller增强器使用说明
 */
//@RestControllerAdvice
public class MyControllerAdvice {

    /**
     * 启动应用后，被 @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，都会作用在 被 @RequestMapping 注解的方法上。
     */

    Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        logger.info("binder.getFieldDefaultPrefix {}",webDataBinder.getFieldDefaultPrefix());
        logger.info("binder.getFieldMarkerPrefix {}",webDataBinder.getFieldMarkerPrefix());
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @ModelAttribute：在Model上设置的值，对于所有被 @RequestMapping 注解的方法中，都可以通过 ModelMap 获取，如下：
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }
//    @RequestMapping("/home")
//    public String home(ModelMap modelMap) {
//        System.out.println(modelMap.get("author"));
//        return (String) modelMap.getAttribute("author");
//    }

//    //或者 通过@ModelAttribute获取
//    @RequestMapping("/home")
//    public String home(@ModelAttribute("author") String author) {
//        System.out.println(author);
//        return author;
//    }

    /**
     * 全局异常捕捉处理
     * @ExceptionHandler 拦截了异常，我们可以通过该注解实现自定义异常处理。其中，@ExceptionHandler 配置的 value 指定需要拦截的异常类型，上面拦截了 Exception.class 这种异常。
     * @param ex
     * @return
     */
//    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex){
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

}
