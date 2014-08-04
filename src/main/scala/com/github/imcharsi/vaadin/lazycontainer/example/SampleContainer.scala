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
