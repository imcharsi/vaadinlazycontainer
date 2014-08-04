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
import javax.servlet.http.HttpServletRequest

import com.vaadin.data.Item
import com.vaadin.data.util.{AbstractBeanContainer, BeanItem}
import com.vaadin.server.VaadinServletService

import scala.collection.JavaConversions
import scala.ref.WeakReference

/**
 * Created by KangWoo,Lee on 14. 5. 23.
 */
abstract class AbstractTableContainer[IdType <: AnyRef, BeanType <: AnyRef](c: Class[_ >: BeanType]) extends AbstractBeanContainer[IdType, BeanType](c) {
  override def removeAllItems(): Boolean = throw new UnsupportedOperationException

  override def removeItem(itemId: scala.AnyRef): Boolean = throw new UnsupportedOperationException

  override def addItem(itemId: IdType, bean: BeanType): BeanItem[BeanType] = throw new UnsupportedOperationException

  override def addItemAfter(previousItemId: IdType, newItemId: IdType, bean: BeanType): BeanItem[BeanType] = throw new UnsupportedOperationException

  override def addItemAt(index: Int, newItemId: IdType, bean: BeanType): BeanItem[BeanType] = throw new UnsupportedOperationException

  override def addItemAt(index: Int): AnyRef = throw new UnsupportedOperationException

  override def addItemAt(index: Int, newItemId: scala.AnyRef): Item = throw new UnsupportedOperationException

  override def addItemAfter(previousItemId: scala.AnyRef): AnyRef = throw new UnsupportedOperationException

  override def addItemAfter(previousItemId: scala.AnyRef, newItemId: scala.AnyRef): Item = throw new UnsupportedOperationException

  override def addItem(itemId: scala.AnyRef): Item = throw new UnsupportedOperationException

  override def addItem(): AnyRef = throw new UnsupportedOperationException

  override def firstItemId(): IdType = throw new UnsupportedOperationException

  override def lastItemId(): IdType = throw new UnsupportedOperationException

  override def isLastId(itemId: scala.Any): Boolean = throw new UnsupportedOperationException

  override def isFirstId(itemId: scala.Any): Boolean = throw new UnsupportedOperationException

  override def nextItemId(itemId: scala.Any): IdType = throw new UnsupportedOperationException

  override def prevItemId(itemId: scala.Any): IdType = throw new UnsupportedOperationException

  override def getItemIds(startIndex: Int, numberOfIds: Int): util.List[IdType] = {
    def utilMethodOne(b: BeanType): (IdType, BeanItem[BeanType]) = {
      val item: BeanItem[BeanType] = createBeanItem(b)
      (getBeanIdResolver.getIdForBean(b), item)
    }
    def utilMethodTwo[A](startIndex: Int)(x: ((IdType, A), Int)): (IdType, Int) = {
      val ((id, _), idx) = x
      (id, idx + startIndex)
    }
    if (needRefreshCache) {
      val tempMap = internalPrepareItemIds(startIndex, numberOfIds).
        map(utilMethodOne)
      id2Item = tempMap.toMap
      id2Index = tempMap.
        zipWithIndex.
        map(utilMethodTwo(startIndex)).
        toMap
      index2Id = id2Index.map(_.swap)
      idList4OrderPreserved = JavaConversions.seqAsJavaList(tempMap.map(a ⇒ a._1))
      needRefreshCache = false
    }
    idList4OrderPreserved
  }

  override def size(): Int = {
    if (needRefreshSize()) {
      sizeVariable = internalSize()
      needRefreshCache = true
    }
    sizeVariable
  }

  override def getItem(itemId: scala.AnyRef): BeanItem[BeanType] = {
    id2Item.
      get(itemId.asInstanceOf[IdType]).
      getOrElse(null)
  }

  override def getIdByIndex(index: Int): IdType = {
    index2Id.get(index).getOrElse(null.asInstanceOf[IdType])
  }

  override def indexOfId(itemId: scala.AnyRef): Int = {
    id2Index.
      get(itemId.asInstanceOf[IdType]).
      getOrElse(-1)
  }

  override def getItemIds: util.List[IdType] = idList4OrderPreserved

  override def containsId(itemId: AnyRef): Boolean = id2Item.isDefinedAt(itemId.asInstanceOf[IdType])

  protected def needRefreshSize(): Boolean = {
    val request = VaadinServletService.getCurrentServletRequest
    if (sizeCache == null || sizeCache.get.filter(_ == request).isEmpty) {
      sizeCache = WeakReference(request)
      true
    } else {
      false
    }
  }

  protected def internalPrepareItemIds(startIndex: Int, numberOfIds: Int): List[BeanType]

  protected def internalSize(): Int

  // size 라는 이름의 변수를 쓸 수가 없어서, 이와 같이 썼다.
  private var sizeVariable: Int = 0
  // 기본 구현으로서, httpservletrequest 의 weak reference 를 cache 필요확인용으로 쓴다.
  private var sizeCache: WeakReference[HttpServletRequest] = null
  private var id2Index: Map[IdType, Int] = Map()
  private var index2Id: Map[Int, IdType] = Map()
  private var id2Item: Map[IdType, BeanItem[BeanType]] = Map()
  // 이것은 사실상 한번 쓰고 버리는 것이긴 한데, 따로 보관해둬야 할 필요가 있나.
  private var idList4OrderPreserved: util.List[IdType] = null
  // size refreshing 과 cache refreshing 은 다르다.
  protected var needRefreshCache: Boolean = true
  // FIXME 상속할 필요가 없는 것 까지 상속한다는 점에서 비용이 너무 큰것 아닌가.
  // 그런데 전체적인 일관성을 위해서는 이렇게 하는 수 밖에 없다.
  // vaadin 은 beanitem 기능을 외부에서 따로 쓸 수 있도록 해두지 않았다.
  // 일단, 지금까지 한 것은 잘된다.

}
