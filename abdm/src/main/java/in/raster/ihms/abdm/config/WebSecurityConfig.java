///************************************************************************************************************
// *  CONFIDENTIAL AND PROPRIETARY
// *
// *  The source code and other information contained herein is the confidential and the exclusive property of
// *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
// *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
// *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
// *  expressly permitted under such license agreement.
// *
// *  Copyright Raster Images Pvt. Ltd.
// *
// *  ALL RIGHTS RESERVED
// ************************************************************************************************************/
//package in.raster.abdm.config;
//
//import in.raster.ihms.commons.security.JwtAuthenticationEntryPoint;
//import in.raster.ihms.commons.security.JwtAuthenticationTokenFilter;
//import in.raster.ihms.commons.security.JwtTokenUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
//        return new JwtAuthenticationEntryPoint();
//    }
//
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Bean
//    public JwtTokenUtil jwtTokenUtil() {
//        return new JwtTokenUtil();
//    }
//
//    /**
//     * Todo - Allowed all api's without token for testing now.Will be enabled in future.
//     * Todo - Http headers disabled for testing now.Will be enabled in future.
//     *
//     * Configure security options.
//     *
//     * @param httpSecurity - http security
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                // we don't need CSRF because our token is invulnerable
//                .csrf().disable()
//
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint()).and()
//
//                // don't create session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//                .authorizeRequests()
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//                // allow anonymous resource requests
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/role/*"
//                ).permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/api/**").permitAll()
//                .anyRequest().authenticated();
//
//        //disable http headers
//        httpSecurity.headers().disable();
//
//        // disable page caching
////         httpSecurity.headers().cacheControl();
//
//        // Allow frame content with in same origin
//        // httpSecurity.headers().frameOptions().sameOrigin();
//
//        // Custom JWT based security filter
//        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
