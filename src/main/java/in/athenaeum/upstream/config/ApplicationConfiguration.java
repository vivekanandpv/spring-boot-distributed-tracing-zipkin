package in.athenaeum.upstream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    private final String upstreamUrl;
    private final RestTemplateBuilder restTemplateBuilder;

    public ApplicationConfiguration(@Value("${app.downstream.url}") String upstreamUrl, RestTemplateBuilder restTemplateBuilder) {
        this.upstreamUrl = upstreamUrl;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplateBuilder.rootUri(upstreamUrl).build();
    }
}
