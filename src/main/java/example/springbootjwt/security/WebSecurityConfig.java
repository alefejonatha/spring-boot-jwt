package example.springbootjwt.security;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final String ACCESS_ADMIN_URL = "/api/v1/login/admin/**";
    private static final String ACCESS_USER_URL = "/api/v1/login/user/**";
    private static final String USERS_API_URL = "/api/v1/users";
    private static final String LOGIN_API_URL = "/api/v1/login";
    private static final String H2_CONSOLE_URL = "/h2-console/**";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private static final String[] SWAGGER_RESOURCES = {
            // -- swagger ui
            "/**.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/configuration/**",
            "/swagger-resources/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                .antMatchers(ACCESS_ADMIN_URL).hasRole(ROLE_ADMIN)
                .antMatchers(ACCESS_USER_URL).hasAnyRole(ROLE_USER, ROLE_ADMIN)

                .antMatchers(H2_CONSOLE_URL).permitAll()
                .antMatchers(SWAGGER_RESOURCES).permitAll()
                .antMatchers(HttpMethod.GET, LOGIN_API_URL).permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_API_URL, USERS_API_URL).permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebdavServlet());
        registrationBean.addUrlMappings(H2_CONSOLE_URL);
        return registrationBean;
    }

}
