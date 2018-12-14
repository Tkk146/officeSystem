package com.oa.controller;

import com.oa.model.ResultVo;
import com.oa.model.Role;
import com.oa.model.ZtreeVo;
import com.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
     private RoleService roleService;
    /*
    * 查询所有角色
    * */
    @RequestMapping("/allRoles.do")
    @ResponseBody
    public List<Role> allRole(){
        List<Role> list=roleService.showAllRole();
        return list;
    }
    /*
* 角色分页
* */
    @RequestMapping("/rolePage.do")
    @ResponseBody
    public ResultVo rolePage(String no,Integer page,Integer limit){
        ResultVo resultVo = roleService.showAllRolePage(no, page, limit);
        return resultVo;
    }
/*
* 根据id查询角色
* */
    @RequestMapping("/queryRoleById.do")
    @ResponseBody
    public Role queryRoleById(Integer rid){
        Role role=roleService.queryRoleById(rid);
        return role ;
    }
    /*
    * 修改角色
    * */
    @RequestMapping("/editRole.do")
    @ResponseBody
    public ResultVo editRole(Role role){
         ResultVo resultVo= new ResultVo();
        boolean b = roleService.editRole(role);
        if(!b){
        resultVo.setCode(-1);
        }
        return resultVo;
    }
/*
* 删除角色
* */
    @RequestMapping("/roleDel.do")
    @ResponseBody
    public ResultVo roleDel(Integer id){
        ResultVo resultVo= new ResultVo();
        boolean b = roleService.roleDel(id);
        if(!b){
            resultVo.setCode(-1);
        }
        return resultVo;
    }

    /*
    * 添加角色
    * */
    @RequestMapping("/roleAdd.do")
    @ResponseBody
    public ResultVo addRole(Role role){
        role.setParentId(3);
        ResultVo resultVo=new ResultVo();
        boolean b = roleService.addRole(role);
        if(!b){
            resultVo.setCode(-1);
        }
        return resultVo;
    }
    @RequestMapping("/queryRoleAuthority.do")
    @ResponseBody
     public ResultVo queryRoleAuthority(Integer rid){
        System.out.println(rid);
        ResultVo resultVo = roleService.queryRoleAuthority(rid);
        return resultVo;
    }
    @RequestMapping
    @ResponseBody
    public Object editRoleAuthority(Integer rid,Integer[] aids ){
         ResultVo resultVo=new ResultVo();

         boolean f= roleService.editRoleAuthority(rid,aids);
         if(!f){
             resultVo.setCode(-1);
         }
         return resultVo;
    }
}
