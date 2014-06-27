package com.github.imcharsi.vaadin.lazycontainer.example;

import java.io.Serializable;

/**
 * Created by KangWoo,Lee on 14. 5. 29.
 */
public class JavaHierarchicalModelA implements Serializable {
    private Integer id, parentId;
    private String name;
    private Integer depth, childrenCount;

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public JavaHierarchicalModelA() {
    }

    public JavaHierarchicalModelA(Integer id, Integer parentId, String name, Integer depth, Integer childrenCount) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.depth = depth;
        this.childrenCount = childrenCount;
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
