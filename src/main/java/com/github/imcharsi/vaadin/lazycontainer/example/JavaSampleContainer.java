package com.github.imcharsi.vaadin.lazycontainer.example;

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
