package com.fxx.mvc;

import com.fxx.dto.FeiDto;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("demo")
@SessionAttributes(types = {Double.class},value = {"f1","f2"})
public class MyController implements EnvironmentAware {

    /**
     * 设置InitBinder绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

    }

    /**
     * 参数加工处理
     */
    @ModelAttribute("comment")
    public String replaceWords(String comment) {
        System.out.println("原数据:" + comment);
        String sw = "-";
        if (comment != null) {
            comment = comment.replace(sw, "F");
        }
        System.out.println("替换数据:" + comment);
        return comment;
    }


    @PostMapping("/hello/{fei}/comment")
    public String hello(@RequestBody FeiDto feixinxin, String age) {
//        model.addAttribute("f1", "f1");
//        model.addAttribute("aa", aa);
//        model.addAttribute("age", age);
//        model.addAttribute("f2", "f2");
//        model.addAttribute("price", new Double(99.99));
        System.out.println(feixinxin.getDate());
        return feixinxin.getFei()+" Hello world " ;
    }

    @PostMapping("/hello1/{fei}/comment")
    public String hello1(@PathVariable String fei, String comment) {
        return fei + " Hello world " + comment;
    }

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
