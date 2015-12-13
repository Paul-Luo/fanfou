package info.fanfou.auth;

import com.webex.clops.oauth.authentication.OAuthAuthenticationToken;
import com.webex.clops.oauth.core.HttpUserSession;
import com.webex.clops.oauth.core.OAuthCredential;
import com.webex.clops.oauth.provider.impl.OAuthProvider;
import com.webex.clops.oauth.provider.impl.ProvidersDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * author : chaoluo
 * date : 2015/12/13
 * depiction :
 */
public class FanfouOAuthAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Log log = LogFactory.getLog(FanfouOAuthAuthenticationFilter.class);

    private ProvidersDefinition providersDefinition = null;

    private String defaultFailureUrl = null;
    private String defaultTargetUrl  = null;

    /**
     * Define the suffix url on which the filter will listen for HTTP requests.
     *
     * @param suffixUrl
     */
    public FanfouOAuthAuthenticationFilter(final String suffixUrl) {
        super(suffixUrl);
    }

    protected FanfouOAuthAuthenticationFilter() {
        super("/j_spring_oauth_security_check");
    }


    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        Assert.notNull(this.providersDefinition, "providersDefinition cannot be null");
        providersDefinition.init();

        if (defaultFailureUrl != null){
            this.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(defaultFailureUrl));
        }
        if (defaultTargetUrl != null){
            ((AbstractAuthenticationTargetUrlRequestHandler)this.getSuccessHandler()).setDefaultTargetUrl(defaultTargetUrl);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        // request parameters
        final Map<String, String[]> parameters = request.getParameterMap();

        // get the right provider
        final OAuthProvider provider = providersDefinition.findProvider(parameters);
        if (provider == null) {
            logger.debug("no provider found for request url : " + request.getRequestURL().toString());
            return null;
        }

        // create OAuth credentials from request
        final OAuthCredential credential = provider.getCredential(new HttpUserSession(request), parameters);

        // and token from credential
        final OAuthAuthenticationToken token = new OAuthAuthenticationToken(credential);
        // set details
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
        logger.debug("token : " + token);

        // authenticate
        Authentication authentication = null;

        authentication = getAuthenticationManager().authenticate(token);

        logger.debug("authentication : " + authentication);
        return authentication;
    }

    public void setProvider(final OAuthProvider provider) {
        this.providersDefinition = new ProvidersDefinition(provider);
    }

    public void setProvidersDefinition(final ProvidersDefinition providersDefinition) {
        this.providersDefinition = providersDefinition;
    }

    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }

    public String getDefaultTargetUrl() {
        return defaultTargetUrl;
    }

    public void setDefaultTargetUrl(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }
}
