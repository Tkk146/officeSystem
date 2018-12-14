package com.oa.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.oa.model.Role;
import com.oa.model.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> queryAllRole();

    List<Role> queryAllRolePage(@Param("name") String name, @Param("begin") Integer begin, @Param("limit") Integer limit);
    Long queryCount();
    Role selectRoleById(Integer id);
    boolean deleteById(Integer id);
    boolean insertRole(Role role);
    boolean updateRole(Role role);
    List<ZtreeVo> queryRoleZtree(Integer rid);

    boolean deleteAuthority(Integer rid);

    boolean addAuthority(@Param("rid") Integer rid, @Param("list") Integer[] aids);
}
