/*
 * Copyright 2002-2018 the original author or authors.
 *
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
 */

package net.sharplab.springframework.security.webauthn.converter;

import net.sharplab.springframework.security.webauthn.attestation.WebAuthnAttestationObject;
import net.sharplab.springframework.security.webauthn.test.CoreTestUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WebAuthnAttestationObjectToBase64StringConverterTest {

    private WebAuthnAttestationObjectToBase64StringConverter target = new WebAuthnAttestationObjectToBase64StringConverter();
    private Base64StringToWebAuthnAttestationObjectConverter base64StringToWebAuthnAttestationObjectConverter = new Base64StringToWebAuthnAttestationObjectConverter();

    @Test
    public void convert_test() {
        WebAuthnAttestationObject input = CoreTestUtil.createWebAuthnAttestationObjectWithFIDOU2FAttestationStatement();
        String result = target.convert(input);
        WebAuthnAttestationObject deserialized = base64StringToWebAuthnAttestationObjectConverter.convert(result);
        assertThat(deserialized).isEqualTo(input);
    }
}
