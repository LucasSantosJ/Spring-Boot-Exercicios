package inicio.spring_boot_aula1.DTO.response;

import java.time.LocalDateTime;

public class ProducerGetResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public ProducerGetResponse(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public ProducerGetResponse() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public void setName(String name) {
        this.name = name;
    }
}
