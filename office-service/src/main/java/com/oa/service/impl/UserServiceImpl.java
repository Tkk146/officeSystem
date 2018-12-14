package com.oa.service.impl;

import com.oa.mapper.UserMapper;
import com.oa.model.*;
import com.oa.service.UserService;
import com.oa.utils.EncrypUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String no, String password) {
        password = EncrypUtil2.md5Pass(password);
        User user = userMapper.selectNameAndPwd(no, password);
        return user;
    }

    @Override
    public boolean updatePassword(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<TauthorityVo> queryUserMenu(Integer id) {
        //查询所有的一级菜单
        List<TauthorityVo> list = userMapper.queryUserMenu(id);
        for (TauthorityVo t : list) {
            //查询所有的二级菜单
            t.setChilds(userMapper.queryChildMenu(t.getId()));
        }
        return list;
    }

    @Override
    public ResultVo queryAllUserPages(String no,Integer flag,Integer begin, Integer limit) {
        ResultVo resultVo = new ResultVo();
            begin =(begin -1)*limit;
        try {
            resultVo.setCode(0);
            Long num = userMapper.queryCount();
            resultVo.setCount(num);//获取记录总数
            List<UserRoleVo> list = userMapper.queryAllUserPages(no,flag,begin,limit);
            resultVo.setData(list);
            resultVo.setMsg("请求成功");
            for (UserRoleVo r : list) {
                List<Role> roleList = r.getRoleList();
                for (Role t : roleList) {
                    r.getRids().add(t.getId());
                    r.getRole().add(t.getInfo());
                    r.getRnames().add(t.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }
    @Override
    public boolean userDel(Integer id) {
        userMapper.deleteUserById(id);
        userMapper.cancelPermissions(id);
        return true ;
    }

    @Override
    public User queryById(Integer id) {
        User user = userMapper.queryUserById(id);
        return user;
    }

    @Override
    public boolean activeUser(User user) {
        userMapper.update(user);
        return true;
    }

    @Override
    public boolean userRoleEdit(Integer id, Integer[] rids) {
        userMapper.cancelPermissions(id);
        userMapper.addPermissions(id,rids);
        return true;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insertUser(user);
    }
}
