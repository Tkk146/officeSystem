package com.oa.model;

import java.util.List;

public class TauthorityVo extends Authority {
    //子菜单
    private List<TauthorityVo> childs;

    @Override
    public String toString() {
        return "TauthorityVo{" +
                "childs=" + childs +
                '}';
    }

    public List<TauthorityVo> getChilds() {
        return childs;
    }

    public void setChilds(List<TauthorityVo> childs) {
        this.childs = childs;
    }

    public TauthorityVo() {
    }
}
