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

import com.github.imcharsi.vaadin.lazycontainer.AbstractTableContainer;
import scala.collection.JavaConversions$;
import scala.collection.immutable.List;

import java.util.LinkedList;

/**
 * Created by KangWoo,Lee on 14. 5. 29.
 */
public class JavaSampleContainer extends AbstractTableContainer<Integer, JavaModelA> {
    public JavaSampleContainer() {
        super(JavaModelA.class);
        setBeanIdResolver(new BeanIdResolver<Integer, JavaModelA>() {
            @Override
            public Integer getIdForBean(JavaModelA bean) {
                return bean.getId();
            }
        });
    }

    @Override
    public List<JavaModelA> internalPrepareItemIds(int startIndex, int numberOfIds) {
        final LinkedList<JavaModelA> list = new LinkedList<>();
        for (int cnt = 0; cnt < numberOfIds; cnt++) {
            list.add(new JavaModelA(startIndex + cnt, null, "node" + cnt));
        }
        return JavaConversions$.MODULE$.asScalaBuffer(list).toList();
    }

    @Override
    public int internalSize() {
        return 100;
    }
}
