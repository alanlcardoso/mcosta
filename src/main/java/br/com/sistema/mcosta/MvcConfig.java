package br.com.sistema.mcosta;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.CssLinkResourceTransformer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Component
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/", "classpath:/resources/static/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
                .addTransformer(new CssLinkResourceTransformer());
    }
    
    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
    
    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new MyErrorPageRegistrar();
    }
    
    private static class MyErrorPageRegistrar implements ErrorPageRegistrar {

        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        }
    }
}
