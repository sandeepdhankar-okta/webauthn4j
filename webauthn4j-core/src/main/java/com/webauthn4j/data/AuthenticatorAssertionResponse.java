/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webauthn4j.data;

import com.webauthn4j.util.ArrayUtil;

import java.util.Arrays;

/**
 * The AuthenticatorAssertionResponse represents an authenticator's response to a
 * client’s request for generation of a new authentication assertion given the WebAuthn
 * Relying Party's challenge and OPTIONAL list of credentials it is aware of.
 *
 * @see <a href ="https://www.w3.org/TR/webauthn-1/#authenticatorassertionresponse">§5.2.2. Web Authentication Assertion (interface AuthenticatorAssertionResponse)</a>
 */
public class AuthenticatorAssertionResponse extends AuthenticatorResponse {

    // ~ Instance fields
    // ================================================================================================

    private final byte[] authenticatorData;
    private final byte[] signature;
    private final byte[] userHandle;

    // ~ Constructor
    // ========================================================================================================

    public AuthenticatorAssertionResponse(byte[] clientDataJSON, byte[] authenticatorData, byte[] signature, byte[] userHandle) {
        super(clientDataJSON);
        this.authenticatorData = authenticatorData;
        this.signature = signature;
        this.userHandle = userHandle;
    }

    public byte[] getAuthenticatorData() {
        return ArrayUtil.clone(authenticatorData);
    }

    public byte[] getSignature() {
        return ArrayUtil.clone(signature);
    }

    public byte[] getUserHandle() {
        return ArrayUtil.clone(userHandle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticatorAssertionResponse that = (AuthenticatorAssertionResponse) o;
        return Arrays.equals(authenticatorData, that.authenticatorData) &&
                Arrays.equals(signature, that.signature) &&
                Arrays.equals(userHandle, that.userHandle);
    }

    @Override
    public int hashCode() {

        int result = Arrays.hashCode(authenticatorData);
        result = 31 * result + Arrays.hashCode(signature);
        result = 31 * result + Arrays.hashCode(userHandle);
        return result;
    }
}
