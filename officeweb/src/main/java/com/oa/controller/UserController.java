package com.oa.controller;

import com.oa.model.ResultVo;
import com.oa.model.TauthorityVo;
import com.oa.model.User;
import com.oa.service.UserService;
import com.oa.utils.EncrypUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
/*
* 登录
* */
    @RequestMapping("/login.do")
    public  String login(String no, String password, HttpSession session){
        System.out.println();
        User user=userService.login(no,password);
         if(user!=null && user.getFlag()!=0){
             session.setAttribute("user",user);
             return "index";
         }else{
             return "login";
         }
    }
    /*
    * 更新
    * */
    @RequestMapping("/querySessionUser.do")
    @ResponseBody
    public  User querySessionUser(HttpSession session){

        User user = (User) session.getAttribute("user");
        return user;
    }
    /*
    * 注销
    * */
    @RequestMapping("/userLoginOut.do")
    public String userLoginOut(HttpSession session){
        session.invalidate();
        return "login";
    }
    /*
    * 上传头像
    * */
    @RequestMapping("/uploadPhoto.do")
    @ResponseBody
    public Map<String,Integer> uploadPhoto(MultipartFile file, HttpSession session, HttpServletRequest request) throws IOException {
        Map<String,Integer> map =new HashMap<>();
        map.put("code",0);
        String contextPath = request.getServletContext().getRealPath("/media/headphoto");
        String filename = file.getOriginalFilename();
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        String realPaths="media/headphoto/"+uuid+"_"+filename;
        File f = new File(contextPath + "/" + uuid + "_" + filename);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user= (User) session.getAttribute("user");
            user.setHeadPhoto(realPaths);
        boolean i = userService.updatePassword(user);
        return map;
    }

    /*
    * 修改密码
    *
    * */
    @RequestMapping("/updatePassword.do")
    @ResponseBody
    public Map<String,Integer> updatePassword(String password, HttpSession session ){
        Map<String,Integer> map =new HashMap<>();
        map.put("code",0);
        password=EncrypUtil2.md5Pass(password);
        User user= (User) session.getAttribute("user");
        user.setPassword(password);
        userService.updatePassword(user);
        return map;
    }
/*
* 初始化菜单
* */
    @RequestMapping("/userMenu.do")
    @ResponseBody
    public List<TauthorityVo> queryUserMenu(HttpSession session){
        User user= (User) session.getAttribute("user");
        List<TauthorityVo> list=userService.queryUserMenu(user.getId());
        return list;
    }
/*
* 查询所有用户
* */
    @RequestMapping("/userAll.do")
    @ResponseBody
    public ResultVo userAll( String no,Integer flag,Integer page, Integer limit){
        ResultVo resultVo = userService.queryAllUserPages(no,flag,page, limit);
        return resultVo;
    }

    /*
    *
    * 激活用户
    * */
    @RequestMapping("/activeUser.do")
    @ResponseBody
     public  ResultVo activeUser(Integer id,Integer flag){
           ResultVo resultVo=new ResultVo();
           User user=userService.queryById(id);
                user.setFlag(flag);
           boolean f= userService.activeUser(user);

        if(!f){
            resultVo.setCode(-1);
        }
        return  resultVo;
     }

     /*
     * 删除用户
     * */
    @RequestMapping("/userDel.do")
    @ResponseBody
    public ResultVo userDel(Integer id){
        ResultVo resultVo=new ResultVo();
         boolean f=  userService.userDel(id);
         if(!f){
             resultVo.setCode(-1);
         }
        return  resultVo;
    }
    @RequestMapping("/userRoleEdit.do")
    @ResponseBody
    public Object userRoleEdit(Integer id,Integer[] rids){
        ResultVo resultVo=new ResultVo();
        boolean f= userService.userRoleEdit(id,rids);
        if(!f){
            resultVo.setCode(-1);
        }
        return  resultVo;
    }
    @RequestMapping("/userAdd.do")
    @ResponseBody
     public Object userAdd(User user){
        ResultVo resultVo=new ResultVo();
        boolean f = userService.addUser(user);
        if(!f){
            resultVo.setCode(-1);
        }
        return  resultVo;
     }

}
