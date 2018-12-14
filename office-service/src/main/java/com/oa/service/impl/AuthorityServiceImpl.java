package com.oa.service.impl;

import com.oa.mapper.AuthorityMapper;
import com.oa.model.Authority;
import com.oa.model.ResultVo;
import com.oa.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public ResultVo showAuthorityPage(String title, Integer page, Integer limit) {
           ResultVo resultVo=new ResultVo();
           page=(page-1)*limit;
           try{
               List<Authority> list=authorityMapper.queryAuthorityPage(title,page,limit);
               resultVo.setData(list);
               Long count=authorityMapper.queryCount();
               resultVo.setCount(count);
               resultVo.setCode(0);
               resultVo.setMsg("请求成功");
           }
           catch (Exception e){
               e.printStackTrace();
           }
        return resultVo;
    }

    @Override
    public boolean authorityDel(Integer id) {
        authorityMapper.deleteAuthorityById(id);
        authorityMapper.deleteRoleAuthority(id);
        return true;
    }

    @Override
    public Authority queryAuthorityById(Integer rid) {

        return authorityMapper.selectAuthorityById(rid);
    }

    @Override
    public boolean editAuthority(Authority authority) {
        return authorityMapper.updateAuthority(authority);
    }

    @Override
    public ResultVo queryAllAuthority() {
        ResultVo resultVo=new ResultVo();
        List<Authority> list=authorityMapper.selectAllAuthority();
        resultVo.setData(list);
        Long count=authorityMapper.queryCount();
        resultVo.setCount(count);
        resultVo.setCode(0);
        resultVo.setMsg("请求成功");
        return resultVo;
    }
}
