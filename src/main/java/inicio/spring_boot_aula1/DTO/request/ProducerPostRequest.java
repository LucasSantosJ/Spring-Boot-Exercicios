package inicio.spring_boot_aula1.DTO.request;

import java.util.Objects;

public class ProducerPostRequest {

    private String name;
    private Long id;

    public ProducerPostRequest() {
    }

    public ProducerPostRequest(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProducerPostRequest that = (ProducerPostRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
