package inicio.spring_boot_aula1.controller;

import inicio.spring_boot_aula1.DTO.request.ProducerPostRequest;
import inicio.spring_boot_aula1.DTO.response.ProducerGetResponse;
import inicio.spring_boot_aula1.domain.Producer;
import inicio.spring_boot_aula1.mapper.ProducerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping("/v1/producers")
public class ProducerController {
    private static  final ProducerMapper PRODUCER_MAPPER = ProducerMapper.INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

    @GetMapping()
    public List<Producer> producers(){
        return Producer.getProducer();
    }

    @GetMapping("/nome")
    public List<Producer> listnome(@RequestParam (required = false) String name) {
        var producer = Producer.getProducer();
        if (producer.isEmpty()) {
            return producer;
        }
        return producer.stream().filter(produce -> produce.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Producer findById(@PathVariable Long id) {
        return Producer.getProducer()
                .stream().filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
            log.info("save producer : {}", headers);

     /*   Producer producer = new Producer();
        producer.setId(ThreadLocalRandom.current().nextLong(100_000));
        producer.setName(producerPostRequest.getName());
        producer.setCreatedAt(LocalDateTime.now());
        Producer.getProducer().add(producer);
       ProducerGetResponse response = new ProducerGetResponse();
       response.setId(producer.getId());
       response.setName(producerPostRequest.getName());
       response.setCreatedAt(LocalDateTime.now());*/
        Producer producer = ProducerMapper.INSTANCE.toProducer(producerPostRequest);
        producer.setId(ThreadLocalRandom.current().nextLong());
        producer.setCreatedAt(LocalDateTime.now());

        Producer.getProducer().add(producer);//percistencia de dados

        ProducerGetResponse response = new ProducerGetResponse();
        response.setId(producer.getId());
        response.setName(producer.getName());
        response.setCreatedAt(producer.getCreatedAt());

       return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
