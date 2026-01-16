package inicio.spring_boot_aula1.controller;

import inicio.spring_boot_aula1.DTO.request.ProducerPostRequest;
import inicio.spring_boot_aula1.DTO.request.ProducerPutRequest;
import inicio.spring_boot_aula1.DTO.response.ProducerGetResponse;
import inicio.spring_boot_aula1.mapper.ProducerMapper;
import inicio.spring_boot_aula1.SERVICES.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producers")
public class ProducerController {
    private static final ProducerMapper PRODUCER_MAPPER = ProducerMapper.INSTANCE;

    private final ProducerService service;

    @Autowired
    public ProducerController(ProducerService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ProducerGetResponse> > listAll(@RequestParam (required = false) String name) {

        var producers = service.findAll(name);

        var producerGetResponses = PRODUCER_MAPPER.toProducerGetResponseList(producers);

        return ResponseEntity.ok(producerGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {

        var producer = service.findByIdOrNotFound(id);

        var producerGetResponse = PRODUCER_MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {

        var producer = PRODUCER_MAPPER.toProducer(producerPostRequest);
        var producerSaved = service.save(producer);

        var producerGetResponse = PRODUCER_MAPPER.toProducerGetResponse(producerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {

     var producerToUpdate = PRODUCER_MAPPER.toProducer(request);

     service.update(producerToUpdate);

     return ResponseEntity.noContent().build();

    }
}
