package inicio.spring_boot_aula1.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Producer {

    private  Long id;
    //@JsonProperty("mudandoNome") notaçao faz com que na requisiçao mude o nome da variavel caso voce precise
    private String name;
    private  LocalDateTime createdAt;

    public Producer(Builder builder){
        this.name = builder.name;
        this.id = builder.id;
        this.createdAt = builder.createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
       private Long id;
       private String name;
       private LocalDateTime createdAt;

       public Builder id(Long id) {
           this.id = id;
           return this; // Retorns o próprio builder para permitir o encadeamento
       }
       public Builder name(String name) {
           this.name = name;
           return this;
       }
       public Builder createdAt(LocalDateTime createdAt) {
           this.createdAt = createdAt;
           return this;
       }

       //metodo que cria o objeto Producer
       public Producer build() {
           return new Producer(this);
       }
    }
    //metodo estático que facilita o inicio da criaçao
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
