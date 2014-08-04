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
package com.github.imcharsi.vaadin.lazycontainer

import java.util

import com.vaadin.data.Collapsible

/**
 * Created by KangWoo,Lee on 14. 5. 23.
 */
abstract class AbstractTreeTableContainer[IdType <: AnyRef, BeanType <: AnyRef, HierarchicalDataType <: AnyRef](c: Class[_ >: BeanType]) extends AbstractTableContainer[IdType, BeanType](c) with Collapsible {

  // 중간을 접으면 접힌 노드의 아래 중에 편 것이 있다해도 검색에서 제외된다.
  protected var expandedStatus2: Set[IdType] = Set()

  // 하위의 개수는, 계산하기 나름이다. 쓸 때, 모든 하위의 개수를 다루고 싶으면 그렇게 하면 되고,
  // 바로 아래의 개수만 다루고 싶으면 그렇게 하면 된다.
  private var childrenInfo = Map[IdType, ChildInfo]()

  protected def convertHierarchicalData2ChildInfo(o: HierarchicalDataType): (IdType, ChildInfo)

  protected def convertHierarchicalData2Model(o: HierarchicalDataType): BeanType

  protected def internalPrepareItemIds2(startIndex: Int, numberOfIds: Int): List[HierarchicalDataType]

  protected def internalGetParent(id: IdType): IdType

  override protected def internalPrepareItemIds(startIndex: Int, numberOfIds: Int): List[BeanType] = {
    val one = internalPrepareItemIds2(startIndex, numberOfIds)
    childrenInfo = one.map(convertHierarchicalData2ChildInfo).toMap
    one.map(convertHierarchicalData2Model)
  }

  def getDepth(id: IdType): Int = childrenInfo(id).depth

  override def setCollapsed(itemId: scala.AnyRef, collapsed: Boolean): Unit = {
    if (collapsed)
      expandedStatus2 = expandedStatus2.-(itemId.asInstanceOf[IdType])
    else
      expandedStatus2 = expandedStatus2.+(itemId.asInstanceOf[IdType])
  }

  override def isCollapsed(itemId: scala.AnyRef): Boolean = !expandedStatus2(itemId.asInstanceOf[IdType])

  override def areChildrenAllowed(itemId: scala.Any): Boolean = childrenInfo(itemId.asInstanceOf[IdType]).childrenCount > 0

  override def getChildren(itemId: scala.Any): util.Collection[_] = throw new UnsupportedOperationException

  override def getParent(itemId: scala.AnyRef): IdType = internalGetParent(itemId.asInstanceOf[IdType])

  override def rootItemIds(): util.Collection[_] = throw new UnsupportedOperationException

  override def setParent(itemId: scala.Any, newParentId: scala.Any): Boolean = throw new UnsupportedOperationException

  override def setChildrenAllowed(itemId: scala.Any, areChildrenAllowed: Boolean): Boolean = throw new UnsupportedOperationException

  override def isRoot(itemId: scala.Any): Boolean = throw new UnsupportedOperationException

  override def hasChildren(itemId: scala.Any): Boolean = throw new UnsupportedOperationException

  override def nextItemId(itemId: Any): IdType = internalGetParent(itemId.asInstanceOf[IdType])
}

case class ChildInfo(val childrenCount: Int, val depth: Int)
