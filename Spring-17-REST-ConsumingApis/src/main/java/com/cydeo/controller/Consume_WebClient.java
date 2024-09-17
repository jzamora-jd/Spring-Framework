package com.cydeo.controller;

import com.cydeo.entity.Genre;
import com.cydeo.entity.MovieCinema;
import com.cydeo.repository.GenreRepository;
import com.cydeo.repository.MovieCinemaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Consume_WebClient {//

    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    private final MovieCinemaRepository movieCinemaRepository;
    private final GenreRepository genreRepository;

    public Consume_WebClient(MovieCinemaRepository movieCinemaRepository, GenreRepository genreRepository) {
        this.movieCinemaRepository = movieCinemaRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/flux-movie-cinemas") //get all the cinemas endpoint
    public Flux<MovieCinema> readAllCinemaFlux(){ // since its more than 1 we have to use flex (async)

        return Flux.fromIterable(movieCinemaRepository.findAll());//reactive way endpoint

    }

//    @GetMapping("/mono-movie-cinema/{id}")
//    public Mono<MovieCinema> readById(@PathVariable("id") Long id){//mono is used to retrieve one at a time by id
//
//        return Mono.just(movieCinemaRepository.findById(id).get());
//
//    }

    @GetMapping("/mono-movie-cinema/{id}")
    public ResponseEntity<Mono<MovieCinema>> readById(@PathVariable("id") Long id){ //response entity type response

        return ResponseEntity.ok(Mono.just(movieCinemaRepository.findById(id).get()));

    }

    @PostMapping("/create-genre")
    public Mono<Genre> createGenre(@RequestBody Genre genre){

        Genre createdGenre = genreRepository.save(genre);

        return Mono.just(createdGenre);
//        return Mono.just(genreRepository.save(genre));

    }

    @DeleteMapping("/delete/genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id){

        genreRepository.deleteById(id);

        return Mono.empty();
    }



//    ---------------------------WEBCLIENT---------------------------


    @GetMapping("/flux")
    public Flux<MovieCinema> readWithWebClient(){//consuming my own endpoint /flux-movie-cinemas in a reactive way

        return webClient
                .get()
                .uri("/flux-movie-cinemas")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(MovieCinema.class);

    }


    @GetMapping("/mono/{id}")
    public Mono<MovieCinema> readMonoWithWebClient(@PathVariable("id") Long id){

        return webClient
                .get()
                .uri("/mono-movie-cinema/{id}",id)
                .retrieve()
                .bodyToMono(MovieCinema.class);

    }





















}
