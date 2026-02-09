package in.raster.ihms.authserver.config;

import in.raster.ihms.commons.security.JwtAuthenticationEntryPoint;
import in.raster.ihms.commons.security.JwtAuthenticationTokenFilter;
import in.raster.ihms.commons.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${ldap.url}")
    private String ldapUrls;

    @Value("${ldap.principal}")
    private String principal;

    @Value("${ldap.partitionSuffix}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;

    @Value("${ldap.enabled}")
    private String ldapEnabled;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .userDetailsService(this.userDetailsService)
//                .passwordEncoder(passwordEncoder());


        authenticationManagerBuilder
                .ldapAuthentication()
                .contextSource()
                .url(ldapUrls + ldapBaseDn)
                .managerDn(ldapSecurityPrincipal)
                .managerPassword(ldapPrincipalPassword)
                .and()
                .userDnPatterns(ldapUserDnPattern);


    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {

        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * LDAP
     *
     * @param httpSecurity
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint()).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/role/*"
                ).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/authserver/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/token/**").permitAll()
                .antMatchers("/api/organizations-by-user/**").permitAll()
                .antMatchers("/api/branches-by-user-organization/**").permitAll()
                .antMatchers("/api/users-by-role/**").permitAll()
                .antMatchers("/api/users-by-type/**").permitAll()
                .antMatchers("/api/users-by-branch-type/**").permitAll()
                .antMatchers("/api/users-by-name/**").permitAll()
                .antMatchers("/api/check-token-expiration/**").permitAll()
                .antMatchers("/api/users-update/**").permitAll()
                .antMatchers("/api/users-update-password/**").permitAll()
                .antMatchers("/api/organizations-active/**").permitAll()
                .antMatchers("/api/branches-by-organization/**").permitAll()
                .antMatchers("/api/users-credit/**").permitAll()
                .antMatchers("/api/departments-by-name/**").permitAll()
                .antMatchers("/storage-service/api/stream/**").permitAll()
                .antMatchers("/his/api/app-settings-by-key/**").permitAll()
                .antMatchers("/his/api/insurance-claims/**").permitAll()
                .antMatchers("/his/api/insurance-claim-estimates-by-claim-id-type/**").permitAll()
                .antMatchers("/his/api/companies/**").permitAll()
                .antMatchers("/his/api/patient-identities-active/**").permitAll()
                .antMatchers("/his/api/patient-medical-documents-by-claim-estimate/**").permitAll()
                .antMatchers("/his/api/patient-ip-visit-beds-by-ip-visit-id/**").permitAll()
                .antMatchers("/storage-service/api/file-path-retrieve/**").permitAll()
                .antMatchers("/his/api/patient-provisional-diagnosis-by-claim/**").permitAll()
                .antMatchers("/his/api/insurance-claim-estimates-recent-by-claim-id/**").permitAll()
                .antMatchers("/his/api/send-otp/**").permitAll()
                .antMatchers("/his/api/generate-otp/**").permitAll()
                .antMatchers("/his/api/verify-otp/**").permitAll()
                .antMatchers("/his/api/clear-all-otp/**").permitAll()
                .antMatchers("/his/api/locations-by-id/**").permitAll()
                .antMatchers("/his/api/software-licenses-active/**").permitAll()
                .antMatchers("/his/api/app-settings/**").permitAll()
                .antMatchers("/his/api/investigation-requests-update-pacs/**").permitAll()
                .antMatchers("/his/api/update-study-instance-id-by-daycare/**").permitAll()
                .antMatchers("/his/websocket/**").permitAll()
                .antMatchers("/his/*.jar").permitAll()
                .antMatchers("/his/*.jnlp").permitAll()
                .antMatchers("/hms/**").permitAll()
                .antMatchers("/onebook/**").permitAll()
                .antMatchers("/onepharmacy/**").permitAll()
                .antMatchers("/onestore/**").permitAll()
                .antMatchers("/pharmacy/api/send-otp/**").permitAll()
                .antMatchers("/pharmacy/api/generate-otp/**").permitAll()
                .antMatchers("/pharmacy/api/verify-otp/**").permitAll()
                .antMatchers("/pharmacy/api/clear-all-otp/**").permitAll()
                .antMatchers("/his/api/whatsapp-webhook-karixrcm/**").permitAll()
                .antMatchers("/his/api/whatsapp-webhook-botify/**").permitAll()
                .anyRequest().authenticated();

        // disable page caching
        httpSecurity.headers().cacheControl();

        //Allows to view pdf content within same domain
        httpSecurity.headers().frameOptions().sameOrigin();

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
