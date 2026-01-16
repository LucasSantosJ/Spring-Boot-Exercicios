package inicio.spring_boot_aula1.REPOSITORY;
import inicio.spring_boot_aula1.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {

    private final List<Producer> producers = new ArrayList<>();

     {
        var abacate = Producer.builder().id(1L).name("Abacate").createdAt(LocalDateTime.now()).build();
        var tomate = Producer.builder().id(2L).name("Tomate").createdAt(LocalDateTime.now()).build();
        var laranja = Producer.builder().id(3L).name("Laranja").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(abacate,tomate,laranja));
    }

    public List<Producer> getProducers() {
        return producers;
    }

}
