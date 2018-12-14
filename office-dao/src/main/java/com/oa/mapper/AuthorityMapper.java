package com.oa.mapper;

import com.oa.model.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper {
    List<Authority> queryAuthorityPage(@Param("title") String title
            ,@Param("begin") Integer begin,@Param("limit") Integer limit);

    Long queryCount();

    boolean deleteAuthorityById(Integer id);
    boolean deleteRoleAuthority(Integer id);
    Authority selectAuthorityById(Integer id);
    boolean updateAuthority(Authority authority);
    List<Authority> selectAllAuthority();
}
