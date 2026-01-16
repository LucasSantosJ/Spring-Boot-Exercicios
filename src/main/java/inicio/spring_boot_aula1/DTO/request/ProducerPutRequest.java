package inicio.spring_boot_aula1.DTO.request;

public class ProducerPutRequest {

    private Long id;
    private String name;

    public ProducerPutRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public ProducerPutRequest() {}

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

    @Override
    public String toString() {
        return "ProducerPutRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
