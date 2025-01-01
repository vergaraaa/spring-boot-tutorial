package com.example.nobsv2.catfact;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.nobsv2.Query;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

    private final RestTemplate restTemplate;

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        // get for object is a GET
        CatFactResponse response = restTemplate.getForObject("https://catfact.ninja/fact?max_length=" + input, CatFactResponse.class);
        CatFactDTO catFactDTO = new CatFactDTO(response.getFact());

        return ResponseEntity.ok(catFactDTO);
    }
    
    
}
