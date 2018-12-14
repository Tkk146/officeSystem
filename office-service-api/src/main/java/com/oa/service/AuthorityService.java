package com.oa.service;

import com.oa.model.Authority;
import com.oa.model.ResultVo;

public interface AuthorityService {
    ResultVo showAuthorityPage(String title, Integer page, Integer limit);

    boolean authorityDel(Integer id);

    Authority queryAuthorityById(Integer rid);

    boolean editAuthority(Authority authority);

    ResultVo queryAllAuthority();

}
