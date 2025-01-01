package com.example.nobsv2.catfact;



import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.nobsv2.Query;
import com.example.nobsv2.exceptions.CatFactsApiDownException;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENGTH = "max_length";


    private final RestTemplate restTemplate;

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        // setup the URL with the string query parameters
        URI uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam(MAX_LENGTH, input)
            .build()
            .toUri();

        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);


        // handle error response with try-catch block
        try {
            // make the request
            ResponseEntity<CatFactResponse> response = restTemplate.exchange(
                uri, 
                HttpMethod.GET, 
                entity, 
                CatFactResponse.class
            );
            
            CatFactDTO catFactDTO = new CatFactDTO(response.getBody().getFact());
    
            return ResponseEntity.ok(catFactDTO);
        } catch (Exception exception) {
            // can throw custom exception
            throw new CatFactsApiDownException();
        }

    }
    
    
}
