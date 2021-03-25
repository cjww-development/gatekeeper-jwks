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

package services

import com.nimbusds.jose.Algorithm
import com.nimbusds.jose.jwk.{KeyUse, RSAKey}
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import play.api.Configuration

import javax.inject.Inject

class DefaultJwksService @Inject()(val config: Configuration) extends JwksService {
  private val keySize = config.get[Int]("jwks.key-generator.size")
  val algorithm: String = config.get[String]("jwks.key-generator.alg")

  override protected val rsaKeyGenerator: RSAKeyGenerator = new RSAKeyGenerator(keySize)
}

trait JwksService {

  val algorithm: String

  protected val rsaKeyGenerator: RSAKeyGenerator

  def generateRSAKey(keyId: String, keyUse: KeyUse): RSAKey = {
    rsaKeyGenerator
      .keyID(keyId)
      .keyUse(keyUse)
      .algorithm(new Algorithm(algorithm))
      .generate()
  }
}
