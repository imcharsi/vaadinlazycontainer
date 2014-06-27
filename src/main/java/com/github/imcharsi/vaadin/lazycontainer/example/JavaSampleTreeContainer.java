package com.github.imcharsi.vaadin.lazycontainer.example;

import com.github.imcharsi.vaadin.lazycontainer.AbstractTreeTableContainer;
import com.github.imcharsi.vaadin.lazycontainer.ChildInfo;
import scala.Tuple2;
import scala.collection.JavaConversions$;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by KangWoo,Lee on 14. 5. 29.
 */
public class JavaSampleTreeContainer extends AbstractTreeTableContainer<Integer, JavaModelA, JavaHierarchicalModelA> {
    public JavaSampleTreeContainer() {
        super(JavaModelA.class);
        setBeanIdResolver(new BeanIdResolver<Integer, JavaModelA>() {
            @Override
            public Integer getIdForBean(JavaModelA javaModelA) {
                return javaModelA.getId();
            }
        });
    }

    @Override
    public Set<Integer> expandedStatus2() {
        final java.util.Set<Integer> set = new TreeSet<>();
        for (int cnt = 0; cnt < 5; cnt++) {
            set.add(cnt);
        }
        return JavaConversions$.MODULE$.asScalaSet(set).toSet();
    }

    @Override
    public int internalSize() {
        return 5;
    }

    @Override
    public Tuple2<Integer, ChildInfo> convertHierarchicalData2ChildInfo(JavaHierarchicalModelA o) {
        return new Tuple2<>(o.getId(), new ChildInfo(o.getChildrenCount(), o.getDepth()));
    }

    @Override
    public JavaModelA convertHierarchicalData2Model(JavaHierarchicalModelA o) {
        return new JavaModelA(o.getId(), o.getParentId(), o.getName());
    }

    @Override
    public List<JavaHierarchicalModelA> internalPrepareItemIds2(int startIndex, int numberOfIds) {
        final java.util.List<JavaHierarchicalModelA> list = new LinkedList<>();
        for (int cnt = 0; cnt < 5; cnt++) {
            list.add(new JavaHierarchicalModelA(cnt, cnt == 0 ? null : cnt - 1, "node" + cnt, cnt, cnt == 4 ? 0 : 1));
        }
        return JavaConversions$.MODULE$.asScalaBuffer(list).toList();
    }

    @Override
    public Integer internalGetParent(Integer id) {
        if (id > 0) return id - 1;
        else return null;
    }
}
