package com.oa.service;

import com.oa.model.ResultVo;
import com.oa.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> showAllRole();

    ResultVo showAllRolePage(String name,Integer page, Integer limit);

    Role queryRoleById(Integer id);
    boolean roleDel(Integer id);
    boolean addRole(Role role);
    boolean editRole(Role role);

    ResultVo queryRoleAuthority(Integer id);

    boolean editRoleAuthority(Integer rid, Integer[] aids);
}
