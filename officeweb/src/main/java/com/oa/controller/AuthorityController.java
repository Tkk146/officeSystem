package com.oa.controller;

import com.oa.model.Authority;
import com.oa.model.ResultVo;
import com.oa.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;
    @RequestMapping("/authorityPage.do")
    @ResponseBody
    public Object authorityPage(String title,Integer page,Integer limit){
        ResultVo resultVo=authorityService.showAuthorityPage(title,page,limit);
        return resultVo;
    }
    @RequestMapping("/authorityDel.do")
    @ResponseBody
    public Object authorityDel(Integer id){
        ResultVo resultVo=new ResultVo();
        boolean b= authorityService.authorityDel(id);
        if(!b){
            resultVo.setCode(-1);
        }
        return resultVo;
    }
    @RequestMapping("/queryAuthorityById.do")
    @ResponseBody
    public Authority queryAuthorityById(Integer rid){
         Authority authority=authorityService.queryAuthorityById(rid);
        return authority;
    }

  @RequestMapping("/editAuthority.do")
  @ResponseBody
  public Object editAuthority(Authority authority){
      ResultVo resultVo=new ResultVo();
      boolean b=authorityService.editAuthority(authority);
      if(!b){
          resultVo.setCode(-1);
      }
      return resultVo;
  }
    @RequestMapping("/authorityAdd.do")
    @ResponseBody
    public Object authorityAdd(Authority authority){
        ResultVo resultVo=new ResultVo();
        boolean b=authorityService.editAuthority(authority);
        if(!b){
            resultVo.setCode(-1);
        }
        return resultVo;
    }
    @RequestMapping("/queryAllAuthority.do")
    @ResponseBody
    public ResultVo queryAllAuthority(){
        ResultVo resultVo = authorityService.queryAllAuthority();
        return resultVo;
    }
}
