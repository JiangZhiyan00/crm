package com.jiangzhiyan.crm.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangzhiyan.crm.base.ResultInfo;
import com.jiangzhiyan.crm.exceptions.ParamsException;
import com.jiangzhiyan.crm.exceptions.UnLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理
 * @author JiangZhiyan
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("code",500);
        mv.addObject("msg","服务器异常,请重试...");
        Object o = request.getAttribute("isView");
        if (o != null){
            boolean isView = (boolean) o;
            //如果返回的是页面
            if (isView){
                mv.setViewName("error");
                //如果是自定义参数异常
                if (e instanceof ParamsException){
                    ParamsException pe = (ParamsException) e;
                    mv.addObject("code", pe.getCode());
                    mv.addObject("msg", pe.getMsg());
                }
                //如果是用户未登录异常
                if (e instanceof UnLoginException){
                    UnLoginException ue = (UnLoginException) e;
                    mv.addObject("code", ue.getCode());
                    mv.addObject("msg", ue.getMsg());
                    mv.setViewName("un_login");
                }
            //如果返回的是数据
            }else {
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("服务器异常,请重试...");
                //如果是自定义参数异常
                if (e instanceof ParamsException){
                    ParamsException pe = (ParamsException) e;
                    resultInfo.setMsg(pe.getMsg());
                    resultInfo.setCode(pe.getCode());
                }
                responseJson(resultInfo,response);
                return null;
            }
        }
        return mv;
    }

    /**
     * 将数据转化为json格式并通过PrintWriter流输出
     * @param r
     * @param response
     */
    private void responseJson(ResultInfo r,HttpServletResponse response){
        PrintWriter out = null;
        response.setContentType("application/json;charset=utf-8");
        try{
            out = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsString(r));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
