package inicio.spring_boot_aula1.SERVICES;

import inicio.spring_boot_aula1.domain.Producer;
import inicio.spring_boot_aula1.REPOSITORY.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProducerService {

    private final ProducerRepository repository;

    @Autowired
    public ProducerService(ProducerRepository repository) {
        this.repository = repository;
    }

    public List<Producer> findAll(String name) {
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Producer findByIdOrNotFound(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "producer nao encontrado"));
    }

    public Producer save(Producer producer) {
        return repository.save(producer);
    }

    public void delete(Long id) {
        var producer = findByIdOrNotFound(id);
        repository.delete(producer);
    }

    public void update(Producer producerToUpdate) {
        var producer =  Producer.builder()
                .id(producerToUpdate.getId())
                .name(producerToUpdate.getName()).build();
    }
}
