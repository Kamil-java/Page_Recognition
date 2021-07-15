package pl.bak.pageRecognition.uri;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.bak.pageRecognition.security.AccessConfig;
import pl.bak.pageRecognition.uri.body_from_request.Root;

@Component
public class CallerTheAPI {
    private final AccessConfig accessConfig;
    private final RestTemplate restTemplate;

    public CallerTheAPI(AccessConfig accessConfig, RestTemplate restTemplate) {
        this.accessConfig = accessConfig;
        this.restTemplate = restTemplate;
    }

    public Root getResponseFromApi(String websiteURL) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(accessConfig.getAuthorizationHeader(), accessConfig.getTokenPrefix() + accessConfig.getSecretKey());

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        String url = UriComponentsBuilder.fromHttpUrl(KlazifyApi.url)
                .queryParam("url", websiteURL).toUriString();


        return restTemplate.postForObject(url, entity, Root.class);
    }
}
