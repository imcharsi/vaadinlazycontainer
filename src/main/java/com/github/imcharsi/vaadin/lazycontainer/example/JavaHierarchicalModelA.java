package com.github.imcharsi.vaadin.lazycontainer.example;

/*
 * #%L
 * lazycontainer
 * %%
 * Copyright (C) 2014 KangWoo, Lee
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
