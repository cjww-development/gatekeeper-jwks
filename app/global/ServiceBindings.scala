/*
 * Copyright 2021 CJWW Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package global

import com.cjwwdev.featuremanagement.controllers.FeatureController
import com.cjwwdev.featuremanagement.models.Features
import controllers.internal.DefaultFeatureController
import filters.{DefaultOutageFilter, DefaultRequestLoggingFilter, OutageFilter, RequestLoggingFilter}
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}

class ServiceBindings extends Module {
  override def bindings(environment: Environment, configuration: Configuration): collection.Seq[Binding[_]] = {
    globals() ++
    filters() ++
    dataStores() ++
    serviceLayer() ++
    orchestrators() ++
    controllers() ++
    apiControllers() ++
    testControllers() ++
    systemControllers()
  }

  private def globals(): Seq[Binding[_]] = Seq(
    bind[Features].to[FeatureDef].eagerly()
  )

  private def filters(): Seq[Binding[_]] = Seq(
    bind[OutageFilter].to[DefaultOutageFilter].eagerly(),
    bind[RequestLoggingFilter].to[DefaultRequestLoggingFilter].eagerly()
  )

  private def dataStores(): Seq[Binding[_]] = Seq(

  )

  private def serviceLayer(): Seq[Binding[_]] = Seq(

  )

  private def orchestrators(): Seq[Binding[_]] = Seq(

  )

  private def controllers(): Seq[Binding[_]] = Seq(

  )

  private def apiControllers(): Seq[Binding[_]] = Seq(

  )

  private def systemControllers(): Seq[Binding[_]] = Seq(
    bind[FeatureController].to[DefaultFeatureController].eagerly()
  )

  private def testControllers(): Seq[Binding[_]] = Seq(

  )
}
