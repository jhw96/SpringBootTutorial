package info.thecodinglive.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // '/'로 시작하는 모든 요청을 다룬다.
				.addResourceLocations("classpath:/assets/","/assets/") // 위의 요청을 처리할 자원을 찾을 위치
				.addResourceLocations("classpath:/templates/", "/templates/")
				.setCachePeriod(60*60*24*365) // 캐시 만료일 1년 설정
				.resourceChain(true) // VersionResourceResolver와 같은 추가 설정들 적용 가능
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
}
