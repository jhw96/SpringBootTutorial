package info.thecodinglive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import info.thecodinglive.ExecuteTimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean // ExecuteTimeInterceptor의 인스턴스를 스프링 컨테이너에 등록
	public ExecuteTimeInterceptor executeTimeInterceptor() {
		return new ExecuteTimeInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(executeTimeInterceptor())
				.addPathPatterns("/**") // 인터셉터가 호출될 경로 설정
				.excludePathPatterns("/"); // 단 '/' 인덱스 페이지 호출될 경우 인터셉터 사용 X
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // '/'로 시작하는 모든 요청을 다룬다.
				.addResourceLocations("classpath:/assets/","/assets/") // 위의 요청을 처리할 자원을 찾을 위치
				.addResourceLocations("classpath:/templates/", "/templates/")
				.setCachePeriod(60*60*24*365) // 캐시 만료일 1년 설정
				.resourceChain(true) // VersionResourceResolver와 같은 추가 설정들 적용 가능
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home"); // 페이지에 데이터를 추가로 전달하지 않고, 단순히 페이지와 URL 연결만 할 때 사용
	}
}
