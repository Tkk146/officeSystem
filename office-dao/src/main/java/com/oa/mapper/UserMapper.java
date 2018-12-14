package com.oa.mapper;

import com.oa.model.TauthorityVo;
import com.oa.model.User;
import com.oa.model.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
   User selectNameAndPwd(@Param("no") String  no, @Param("password") String password );

 boolean  update(User user);
    List<TauthorityVo> queryUserMenu(Integer id);
    List<TauthorityVo> queryChildMenu(Integer id);
    List<UserRoleVo> queryAllUserPages(@Param("no") String no,@Param("flag") Integer flag
            ,@Param("begin") Integer begin,@Param("limit") Integer limit);
    Long queryCount();
    User queryUserById(Integer id);
    boolean cancelPermissions(Integer id);
    boolean addPermissions(@Param("id") Integer id,@Param("list") Integer[] rids);
    Boolean deleteUserById(Integer id);

    boolean insertUser(User user);
}
