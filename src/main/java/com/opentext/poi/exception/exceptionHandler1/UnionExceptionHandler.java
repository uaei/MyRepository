package com.opentext.poi.exception.exceptionHandler1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @Author GuYaWei
 * @created 2020/9/15 17:14
 * @description
 */
//@RestControllerAdvice
public class UnionExceptionHandler {

    Logger logger = LoggerFactory.getLogger(UnionExceptionHandler.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.info("binder.getFieldDefaultPrefix {}",binder.getFieldDefaultPrefix());
        logger.info("binder.getFieldMarkerPrefix {}",binder.getFieldMarkerPrefix());
    }
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "harry");
    }
    /**
     * Description : 全局异常捕捉处理
     * Group :
     *
     * @author honghh
     * @date  2019/4/1 0001 10:34
     * @param ex
     * @return
     */
    @ExceptionHandler(RRException.class)
    public R apiExceptionHandler(RRException ex) {
        logger.error("ApiException 异常抛出：{}", ex);
        return R.error(ex.getCode(),ex.getMsg());
    }


}
