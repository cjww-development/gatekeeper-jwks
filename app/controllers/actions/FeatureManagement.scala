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

package controllers.actions

import com.cjwwdev.featuremanagement.services.FeatureService
import global.Features
import models.ErrorResponses
import play.api.mvc.{BaseController, Result}

import scala.concurrent.Future

trait FeatureManagement {
  self: BaseController =>

  val featureService: FeatureService

  def featureGuard(featureState: Boolean)(result: => Future[Result]): Future[Result] = {
    if(featureState) result else Future.successful(ServiceUnavailable(ErrorResponses.serviceUnavailable))
  }
}
