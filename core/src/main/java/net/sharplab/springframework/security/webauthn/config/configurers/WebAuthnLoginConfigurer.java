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

package net.sharplab.springframework.security.webauthn.config.configurers;

import net.sharplab.springframework.security.webauthn.WebAuthnFirstOfMultiFactorDelegatingAuthenticationProvider;
import net.sharplab.springframework.security.webauthn.WebAuthnProcessingFilter;
import net.sharplab.springframework.security.webauthn.context.provider.WebAuthnAuthenticationContextProvider;
import net.sharplab.springframework.security.webauthn.metadata.MetadataEndpointFilter;
import net.sharplab.springframework.security.webauthn.metadata.MetadataProvider;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static net.sharplab.springframework.security.webauthn.WebAuthnProcessingFilter.*;

/**
 * Adds webAuthnLogin authentication. All attributes have reasonable defaults making all
 * parameters are optional. If no {@link #loginPage(String)} is specified, a default login
 * page will be generated by the framework.
 * <p>
 * <h2>Security Filters</h2>
 * <p>
 * The following Filters are populated
 * <p>
 * <ul>
 * <li>{@link WebAuthnProcessingFilter}</li>
 * <li>{@link WebAuthnFirstOfMultiFactorDelegatingAuthenticationProvider}</li>
 * </ul>
 * <p>
 * <h2>Shared Objects Created</h2>
 * <p>
 * No shared objects are populated
 * <p>
 * <h2>Shared Objects Used</h2>
 * <p>
 * The following shared objects are used:
 * <p>
 * <ul>
 * <li>{@link org.springframework.security.authentication.AuthenticationManager}</li>
 * </ul>
 */
public final class WebAuthnLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, WebAuthnLoginConfigurer<H>, WebAuthnProcessingFilter> {

    private AuthenticationTrustResolver authenticationTrustResolver;
    private MetadataProvider metadataProvider;

    public WebAuthnLoginConfigurer() {
        super(new WebAuthnProcessingFilter(), null);

        usernameParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        passwordParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        credentialIdParameter(SPRING_SECURITY_FORM_CREDENTIAL_ID_KEY);
        clientDataParameter(SPRING_SECURITY_FORM_CLIENTDATA_KEY);
        authenticatorDataParameter(SPRING_SECURITY_FORM_AUTHENTICATOR_DATA_KEY);
        signatureParameter(SPRING_SECURITY_FORM_SIGNATURE_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(H http) throws Exception {
        super.init(http);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(H http) throws Exception {
        super.configure(http);
        http.addFilterAfter(
                new MetadataEndpointFilter(metadataProvider, authenticationTrustResolver), SessionManagementFilter.class);
    }

    /**
     * The HTTP parameter to look for the username when performing authentication. Default
     * is "username".
     *
     * @param usernameParameter the HTTP parameter to look for the username when
     *                          performing authentication
     * @return the {@link FormLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> usernameParameter(String usernameParameter) {
        this.getAuthenticationFilter().setUsernameParameter(usernameParameter);
        return this;
    }

    /**
     * The HTTP parameter to look for the password when performing authentication. Default
     * is "password".
     *
     * @param passwordParameter the HTTP parameter to look for the password when
     *                          performing authentication
     * @return the {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> passwordParameter(String passwordParameter) {
        getAuthenticationFilter().setPasswordParameter(passwordParameter);
        return this;
    }


    /**
     * The HTTP parameter to look for the password when performing authentication. Default
     * is "password".
     *
     * @param credentialIdParameter the HTTP parameter to look for the credentialId when
     *                              performing authentication
     * @return the {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> credentialIdParameter(String credentialIdParameter) {
        this.getAuthenticationFilter().setCredentialIdParameter(credentialIdParameter);
        return this;
    }

    /**
     * The HTTP parameter to look for the password when performing authentication. Default
     * is "password".
     *
     * @param clientDataParameter the HTTP parameter to look for the clientData when
     *                            performing authentication
     * @return the {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> clientDataParameter(String clientDataParameter) {
        this.getAuthenticationFilter().setClientDataParameter(clientDataParameter);
        return this;
    }

    /**
     * The HTTP parameter to look for the password when performing authentication. Default
     * is "password".
     *
     * @param authenticatorDataParameter the HTTP parameter to look for the authenticatorData when
     *                                   performing authentication
     * @return the {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> authenticatorDataParameter(String authenticatorDataParameter) {
        this.getAuthenticationFilter().setAuthenticatorDataParameter(authenticatorDataParameter);
        return this;
    }

    /**
     * The HTTP parameter to look for the password when performing authentication. Default
     * is "password".
     *
     * @param signatureParameter the HTTP parameter to look for the signature when
     *                           performing authentication
     * @return the {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> signatureParameter(String signatureParameter) {
        this.getAuthenticationFilter().setSignatureParameter(signatureParameter);
        return this;
    }

    /**
     * Forward Authentication Failure Handler
     *
     * @param forwardUrl the target URL in case of failure
     * @return he {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> failureForwardUrl(String forwardUrl) {
        failureHandler(new ForwardAuthenticationFailureHandler(forwardUrl));
        return this;
    }

    /**
     * Forward Authentication Success Handler
     *
     * @param forwardUrl the target URL in case of success
     * @return he {@link WebAuthnLoginConfigurer} for additional customization
     */
    public WebAuthnLoginConfigurer<H> successForwardUrl(String forwardUrl) {
        successHandler(new ForwardAuthenticationSuccessHandler(forwardUrl));
        return this;
    }


    public WebAuthnLoginConfigurer<H> webAuthnAuthenticationContextProvider(WebAuthnAuthenticationContextProvider webAuthnAuthenticationContextProvider) {
        this.getAuthenticationFilter().setWebAuthnAuthenticationContextProvider(webAuthnAuthenticationContextProvider);
        return this;
    }


    public WebAuthnLoginConfigurer<H> authenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
        this.authenticationTrustResolver = authenticationTrustResolver;
        return this;
    }

    public WebAuthnLoginConfigurer<H> metadataProvider(MetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
        return this;
    }

    /**
     * <p>
     * Specifies the URL to send users to if login is required. If used with
     * {@link WebSecurityConfigurerAdapter} a default login page will be generated when
     * this attribute is not specified.
     * </p>
     *
     * @param loginPage login page
     * @return the {@link WebAuthnLoginConfigurer} for additional customization
     */
    @Override
    public WebAuthnLoginConfigurer<H> loginPage(String loginPage) {
        return super.loginPage(loginPage);
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    public static WebAuthnLoginConfigurer webAuthnLogin() {
        return new WebAuthnLoginConfigurer();
    }

}
