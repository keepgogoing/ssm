package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/upload")
public class TbUploadFile {
    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("/method1")
    @ResponseBody
    public String method1(@RequestParam("file")CommonsMultipartFile file){
        //用来检测程序的运行时间
        long startTime=System.currentTimeMillis();
        System.out.println("filename:"+file.getOriginalFilename());
        try {
            //获取输出流
            OutputStream os = new FileOutputStream("E:\\IdeaProject\\uploadtest\\"+new Date().getTime()+file.getOriginalFilename());
            //获取输入流
            InputStream is = file.getInputStream();
            int temp;
            while((temp=is.read())!=(-1)){
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间:"+String.valueOf(endTime-startTime)+"ms");
        return "success!";
    }


    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("/method2")
    @ResponseBody
    public String method2(@RequestParam("file")CommonsMultipartFile file){
        //用来检测程序的运行时间
        long startTime=System.currentTimeMillis();
        System.out.println("filename:"+file.getOriginalFilename());

        String path="E:\\IdeaProject\\uploadtest\\"+new Date().getTime()+file.getOriginalFilename();
        File newFile = new File(path);
        //通过CommonsMultipartFile的方法直接写文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间:"+String.valueOf(endTime-startTime)+"ms");
        return "success!";
    }

    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("/method3")
    @ResponseBody
    public String method3(HttpServletRequest request) throws IOException {
        //用来检测程序的运行时间
        long startTime=System.currentTimeMillis();
        //将当前上下文初始化给CommonsMultipartResolver（多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctyoe="mutipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest)request;
            //获取mutpartRequest中所有的文件名
            Iterator iter = multipartHttpServletRequest.getFileNames();
            while (iter.hasNext()){
                //一次遍历所有文件
                MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
                if(file!=null){
                    String path="E:\\IdeaProject\\uploadtest\\"+new Date().getTime()+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间:"+String.valueOf(endTime-startTime)+"ms");
        return "success!";
    }

}
