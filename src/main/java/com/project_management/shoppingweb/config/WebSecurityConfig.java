package com.project_management.shoppingweb.config;


import com.project_management.shoppingweb.service.AuthUserService;
import com.project_management.shoppingweb.util.MD5Util;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

	@Bean
	UserDetailsService authuserService(){ //2
		return new AuthUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authuserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.encrypt(charSequence + PASSWORD_KEY);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encodeS = MD5Util.encrypt(charSequence + PASSWORD_KEY);
                return encodeS.equals(s);
            }
        });

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/register","/admin/addregister","/admin/login",
						"/message/getMessage","/admin/loginReturn",
						"/message/getLoginMessage","/admin/loginFailReturn",
						"/admin/logoutReturn","/transaction/getBuyByPage.do","/transaction/getSellByPage.do")
                    .permitAll()
				.anyRequest().authenticated() //4
				.and()
				.formLogin()
//					.loginPage("/admin/login")
//					.loginPage("/admin/addlogin")
                     .loginProcessingUrl("/admin/addlogin")
				     .successForwardUrl("/admin/loginReturn")
					.failureForwardUrl("/admin/loginFailReturn")
					.permitAll() //5s
				.and()
				.logout().permitAll()
				.logoutUrl("/admin/logout")
				.logoutSuccessUrl("/admin/logoutReturn"); //6
		http
			.cors()
			.and().csrf().disable();


	}



//	/*
//	 * 登录session key
//	 * */
//
//	public final static String SESSION_KEY = "admin";
//
//	@Bean
//    public SecurityInterceptor getSecurityInterceptor() {
//        return new SecurityInterceptor();
//    }
//
//	public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//
//
//        addInterceptor.excludePathPatterns("/error");
//
//        addInterceptor.excludePathPatterns("/admin/login**");
//        addInterceptor.excludePathPatterns("/admin/addlogin");
//        addInterceptor.excludePathPatterns("/admin/addregister");
//        addInterceptor.excludePathPatterns("/admin/register");
//
//
//        // 拦截配置
//        addInterceptor.addPathPatterns("/admin/**");
//    }
//
//	 private class SecurityInterceptor extends HandlerInterceptorAdapter {
//
//	        @Override
//	        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//	                throws Exception {
//	            HttpSession session = request.getSession();
//	            if (session.getAttribute(SESSION_KEY) != null) {
//	            	return true;
//	            }
//
//	            // 跳转登录
//	            String url = "/admin/login";
//	            response.sendRedirect(url);
//	            return false;
//	        }
//	    }
}
