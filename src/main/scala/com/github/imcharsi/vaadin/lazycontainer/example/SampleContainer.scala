package com.github.imcharsi.vaadin.lazycontainer.example

import com.github.imcharsi.vaadin.lazycontainer.AbstractTableContainer
import com.vaadin.data.util.AbstractBeanContainer.BeanIdResolver

/**
 * Created by KangWoo,Lee on 14. 6. 27.
 */
class SampleContainer extends AbstractTableContainer[Option[Int], ModelA](classOf[ModelA]) {
  setBeanIdResolver(new BeanIdResolver[Option[Int], ModelA] {
    override def getIdForBean(p1: ModelA): Option[Int] = p1.id
  })

  override protected def internalPrepareItemIds(startIndex: Int, numberOfIds: Int): List[ModelA] = {
    0.until(numberOfIds).map { x =>
      ModelA(Some(startIndex + x), None, Some(f"node$x"))
    }.toList
  }

  override protected def internalSize(): Int = 100
}
