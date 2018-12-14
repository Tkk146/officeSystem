package com.oa.service.impl;

import com.oa.mapper.RoleMapper;
import com.oa.model.ResultVo;
import com.oa.model.Role;
import com.oa.model.ZtreeVo;
import com.oa.service.RoleService;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> showAllRole() {
        List<Role> list= roleMapper.queryAllRole();
        return list;
    }

    @Override
    public ResultVo showAllRolePage(String name,Integer page, Integer limit) {
            ResultVo resultVo=new ResultVo();
            page=(page-1)*limit;
            try{
                List<Role> roles = roleMapper.queryAllRolePage(name,page,limit);
                resultVo.setData(roles);
                resultVo.setCode(0);
                Long count=  roleMapper.queryCount();
                resultVo.setCount(count);
                resultVo.setMsg("请求成功");
            }catch (Exception e){
               e.printStackTrace();
            }
        return resultVo;
    }

    @Override
    public Role queryRoleById(Integer id) {
         Role role = roleMapper.selectRoleById(id);
        return role;
    }

    @Override
    public boolean roleDel(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public boolean addRole(Role role) {

        return roleMapper.insertRole(role);
    }

    @Override
    public boolean editRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public ResultVo queryRoleAuthority(Integer id) {
           ResultVo resultVo=new ResultVo();
           List<ZtreeVo> ztreeVos = roleMapper.queryRoleZtree(id);
           resultVo.setData(ztreeVos);
        return resultVo;
    }

    @Override
    public boolean editRoleAuthority(Integer rid, Integer[] aids) {
       roleMapper.deleteAuthority(rid);
       roleMapper.addAuthority(rid,aids);
        return true;
    }
}
