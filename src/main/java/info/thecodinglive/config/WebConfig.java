package info.thecodinglive.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // '/'�� �����ϴ� ��� ��û�� �ٷ��.
				.addResourceLocations("classpath:/assets/","/assets/") // ���� ��û�� ó���� �ڿ��� ã�� ��ġ
				.addResourceLocations("classpath:/templates/", "/templates/")
				.setCachePeriod(60*60*24*365) // ĳ�� ������ 1�� ����
				.resourceChain(true) // VersionResourceResolver�� ���� �߰� ������ ���� ����
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
}
