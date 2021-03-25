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

import com.nimbusds.jose.jwk.{KeyUse, RSAKey}
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import helpers.Assertions
import org.scalatestplus.play.PlaySpec

class JwksServiceSpec extends PlaySpec with Assertions {

  val testService: JwksService = new JwksService {
    override val algorithm: String = "RS256"
    override protected val rsaKeyGenerator: RSAKeyGenerator = new RSAKeyGenerator(2048)
  }

  "generateRSAKey" should {
    "return a new RSAKey with the correct key id" in {
      assertOutput(testService.generateRSAKey("test-key-id", KeyUse.SIGNATURE)) { key =>

        println(key.toJSONString)

        val x =RSAKey.parse(key.toJSONString)


        key.size() mustBe 2048
        key.getKeyID mustBe "test-key-id"
        key.getAlgorithm.getName mustBe "RS256"
        key.getKeyUse mustBe KeyUse.SIGNATURE

        x.size() mustBe 2048
        x.getKeyID mustBe "test-key-id"
        x.getAlgorithm.getName mustBe "RS256"
        x.getKeyUse mustBe KeyUse.SIGNATURE
      }
    }
  }
}
