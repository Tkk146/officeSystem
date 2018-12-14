package com.oa.service;

import com.oa.model.ResultVo;
import com.oa.model.TauthorityVo;
import com.oa.model.User;

import java.util.List;

public interface UserService {
   User login(String no, String password);

   boolean updatePassword(User user);
    List<TauthorityVo> queryUserMenu(Integer id);
    ResultVo queryAllUserPages(String no,Integer flag,Integer begin, Integer limit);
    boolean userDel(Integer id);
    User queryById(Integer id);
    boolean activeUser(User user);
    boolean userRoleEdit(Integer id, Integer[] rids);
    boolean addUser(User user);
}
