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

import net.sharplab.springframework.security.webauthn.client.Origin;
import net.sharplab.springframework.security.webauthn.client.challenge.Challenge;

import java.io.Serializable;

/**
 * RelyingParty
 */
public class RelyingParty implements Serializable {

    private Origin origin;
    private String rpId;
    private Challenge challenge;

    public RelyingParty(Origin origin, String rpId, Challenge challenge) {
        this.origin = origin;
        this.rpId = rpId;
        this.challenge = challenge;
    }

    public Origin getOrigin() {
        return origin;
    }

    public String getRpId() {
        return rpId;
    }

    public Challenge getChallenge() {
        return challenge;
    }

}
