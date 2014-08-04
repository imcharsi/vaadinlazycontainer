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
package com.github.imcharsi.vaadin.lazycontainer.example

import com.github.imcharsi.vaadin.lazycontainer.{AbstractTreeTableContainer, ChildInfo}
import com.vaadin.data.util.AbstractBeanContainer.BeanIdResolver

/**
 * Created by KangWoo,Lee on 14. 6. 27.
 */
class SampleTreeContainer extends AbstractTreeTableContainer[Option[Int], ModelA, HierarchicalModelA](classOf[ModelA]) {
  setBeanIdResolver(new BeanIdResolver[Option[Int], ModelA] {
    override def getIdForBean(p1: ModelA): Option[Int] = p1.id
  })

  override protected def internalSize(): Int = 5

  override protected def internalGetParent(id: Option[Int]): Option[Int] = id.map(_ - 1)

  override protected def internalPrepareItemIds2(startIndex: Int, numberOfIds: Int): List[HierarchicalModelA] = {
    0.until(5).map { x =>
      HierarchicalModelA(Some(x), if (x == 0) None else Some(x - 1), Some(f"node$x"), Some(x), if (x == 4) Some(0) else Some(1))
    }.toList
  }

  override protected def convertHierarchicalData2ChildInfo(o: HierarchicalModelA): (Option[Int], ChildInfo) = (o.id, ChildInfo(o.subCount.get, o.depth.get))

  override protected def convertHierarchicalData2Model(o: HierarchicalModelA): ModelA = ModelA(o.id, o.parentId, o.name)
}
