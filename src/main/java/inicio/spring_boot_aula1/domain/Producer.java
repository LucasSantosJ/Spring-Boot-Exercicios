package inicio.spring_boot_aula1.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producer {

    private Long id;
    //@JsonProperty("mudandoNome") notaçao faz com que na requisiçao mude o nome da variavel caso voce precise
    private String name;
    private LocalDateTime createdAt;
    private static  List<Producer> producer = new ArrayList<>();

    public Producer(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Producer() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setName(String name) {
        this.name = name;
    }

    static {
        var abacate = new Producer("Abacate", 1L);
        var tomate = new Producer("Tomate", 2L);
        var laranja = new Producer("Laranja", 3L);

        producer.addAll(List.of(abacate,tomate,laranja));
    }

    public static List<Producer> getProducer() {
        return producer;
    }

    public static void setProducer(List<Producer> producer) {
        Producer.producer = producer;
    }
}
