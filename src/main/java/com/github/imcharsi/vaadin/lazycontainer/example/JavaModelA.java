package com.github.imcharsi.vaadin.lazycontainer.example;

import java.io.Serializable;

/**
 * Created by KangWoo,Lee on 14. 5. 29.
 */
public class JavaModelA implements Serializable {
    private Integer id, parentId;
    private String name;

    public JavaModelA() {
    }

    public JavaModelA(Integer id, Integer parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
