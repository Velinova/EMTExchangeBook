package velin.finki.emt.exchangebook.userborrowings.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import velin.finki.emt.exchangebook.userborrowings.application.BookCatalog;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Book;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;

import java.util.Collections;
import java.util.List;

@Service
class BookCatalogClient implements BookCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookCatalogClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    BookCatalogClient(@Value("${app.userborrowings.url}") String serverUrl,
                      @Value("${app.userborrowings.connect-timeout-ms}") int connectTimeout,
                      @Value("${app.userborrowings.read-timeout-ms}") int readTimeout
    ) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    BookCatalogClient(RestTemplate restTemplate, String serverUrl) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<Book> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/books").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Book>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving books", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Book findById(BookId id) {
        try {
            return restTemplate.exchange(uri().path("/api/books/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Book>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving book by id", ex);
            return null;
        }
    }
}

