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

package net.sharplab.springframework.security.webauthn.context;

import net.sharplab.springframework.security.webauthn.attestation.authenticator.WebAuthnAuthenticatorData;
import net.sharplab.springframework.security.webauthn.client.CollectedClientData;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * WebAuthnAuthenticationContext
 */
public class WebAuthnAuthenticationContext implements Serializable {

    //~ Instance fields ================================================================================================

    private String credentialId;
    private byte[] rawClientData;
    private byte[] rawAuthenticatorData;
    private String clientDataJson;
    private CollectedClientData collectedClientData;
    private WebAuthnAuthenticatorData authenticatorData;
    private byte[] signature;
    private RelyingParty relyingParty;
    private Authentication currentAuthentication;


    public WebAuthnAuthenticationContext(String credentialId,
                                         byte[] rawClientData,
                                         byte[] rawAuthenticatorData,
                                         String clientDataJson,
                                         CollectedClientData collectedClientData,
                                         WebAuthnAuthenticatorData authenticatorData,
                                         byte[] signature,
                                         RelyingParty relyingParty,
                                         Authentication currentAuthentication) {
        this.credentialId = credentialId;
        this.rawClientData = rawClientData;
        this.rawAuthenticatorData = rawAuthenticatorData;
        this.clientDataJson = clientDataJson;
        this.collectedClientData = collectedClientData;
        this.authenticatorData = authenticatorData;
        this.signature = signature;
        this.relyingParty = relyingParty;
        this.currentAuthentication = currentAuthentication;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public byte[] getRawClientData() {
        return rawClientData;
    }

    public String getClientDataJson() {
        return clientDataJson;
    }

    public CollectedClientData getCollectedClientData() {
        return collectedClientData;
    }

    public byte[] getRawAuthenticatorData() {
        return rawAuthenticatorData;
    }

    public WebAuthnAuthenticatorData getAuthenticatorData() {
        return authenticatorData;
    }

    public byte[] getSignature() {
        return signature;
    }

    public RelyingParty getRelyingParty() {
        return relyingParty;
    }

    public Authentication getCurrentAuthentication() {
        return currentAuthentication;
    }

    public void setCurrentAuthentication(Authentication currentAuthentication) {
        this.currentAuthentication = currentAuthentication;
    }
}
