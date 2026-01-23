package inicio.spring_boot_aula1.service;

import inicio.spring_boot_aula1.domain.Producer;
import inicio.spring_boot_aula1.repository.ProducerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerServiceTest {

    @InjectMocks
    private ProducerService service;
    @Mock
    private ProducerRepository repository;
    private List<Producer> producerList;

    @BeforeEach
    void init() {
        var manga = Producer.builder().id(1L).name("Manga").createdAt(LocalDateTime.now()).build();
        var arroz = Producer.builder().id(2L).name("Arroz").createdAt(LocalDateTime.now()).build();
        var feijao = Producer.builder().id(3L).name("Feijao").createdAt(LocalDateTime.now()).build();

        producerList = new ArrayList<>(List.of(manga, arroz, feijao));
    }
    @Test
    @DisplayName("findAll returns a list with all producers when argument is null")
    @Order(1)
    void findAllReturnProducersIfNull() {
        BDDMockito.when(repository.findAll()).thenReturn(producerList);

        var producers = service.findAll(null);
        Assertions.assertThat(producers).isNotNull().hasSameSizeAs(producerList);

    }

    @Test
    @DisplayName("findAll return list whith found object when name is null")
    @Order(2)
    void findByNameReturnProducerList() {
        var producer = producerList.getFirst();
        var expectedProducerFound = Collections.singletonList(producer);
        BDDMockito.when(repository.findByName(producer.getName())).thenReturn(expectedProducerFound);

        var producerFound = service.findAll(producer.getName());
        Assertions.assertThat(producerFound).containsAll(expectedProducerFound);

    }

    @Test
    @DisplayName("findAll return empty list when name is not found")
    @Order(3)
    void findByNameReturnEmptyList() {
       var name = "not-found";
       BDDMockito.when(repository.findByName(name)).thenReturn(Collections.emptyList());

       var producerFound = service.findAll(name);
        Assertions.assertThat(producerFound).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findById returns a producer with given id")
    @Order(4)
    void findById_ReturnProducerIfFound() {
        var expectedProducer = producerList.getFirst();
        BDDMockito.when(repository.findById(expectedProducer.getId())).thenReturn(Optional.of(expectedProducer));

        var producers = service.findByIdOrNotFound(expectedProducer.getId());

        Assertions.assertThat(producers).isEqualTo(expectedProducer);
    }


    @Test
    @DisplayName("findById throws ResponseStatusException when producer is not")
    @Order(5)
    void findById_ResponseStatusProducerException() {
        var expectedProducer = producerList.getFirst();
        BDDMockito.when(repository.findById(expectedProducer.getId())).thenReturn(Optional.empty());

      Assertions.assertThatException()
              .isThrownBy(()-> service.findByIdOrNotFound(expectedProducer.getId()))
              .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("save create s producer")
    @Order(6)
    void save_CreateProducer() {
        var producerToSave = Producer.builder().id(1L).name("bananas maduras").createdAt(LocalDateTime.now()).build();
        BDDMockito.when(repository.save(producerToSave)).thenReturn(producerToSave);

        var savedProducer = service.save(producerToSave);
        Assertions.assertThat(savedProducer).isEqualTo(producerToSave).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("delet removes a producer")
    @Order(7)
    void delet_removeProducer() {
        var producerToDelet = producerList.getFirst();

        BDDMockito.when(repository.findById(producerToDelet.getId())).thenReturn(Optional.of(producerToDelet));
        BDDMockito.doNothing().when(repository).delete(producerToDelet);

        Assertions.assertThatException().isThrownBy(() -> service.delete(producerToDelet.getId()));

    }

    @Test
    @DisplayName("delet thorows ResponseSatutusException when producer is not found")
    @Order(8)
    void delet_thorow() {
        var producerToDelet = producerList.getFirst();

        BDDMockito.when(repository.findById(producerToDelet.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(()-> service.delete(producerToDelet.getId()))
                .isInstanceOf(ResponseStatusException.class);

    }

}