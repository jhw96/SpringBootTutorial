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
	
	@Bean // ExecuteTimeInterceptor�� �ν��Ͻ��� ������ �����̳ʿ� ���
	public ExecuteTimeInterceptor executeTimeInterceptor() {
		return new ExecuteTimeInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(executeTimeInterceptor())
				.addPathPatterns("/**") // ���ͼ��Ͱ� ȣ��� ��� ����
				.excludePathPatterns("/"); // �� '/' �ε��� ������ ȣ��� ��� ���ͼ��� ��� X
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // '/'�� �����ϴ� ��� ��û�� �ٷ��.
				.addResourceLocations("classpath:/assets/","/assets/") // ���� ��û�� ó���� �ڿ��� ã�� ��ġ
				.addResourceLocations("classpath:/templates/", "/templates/")
				.setCachePeriod(60*60*24*365) // ĳ�� ������ 1�� ����
				.resourceChain(true) // VersionResourceResolver�� ���� �߰� ������ ���� ����
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home"); // �������� �����͸� �߰��� �������� �ʰ�, �ܼ��� �������� URL ���Ḹ �� �� ���
	}
}
