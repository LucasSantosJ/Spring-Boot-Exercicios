package inicio.spring_boot_aula1.repository;

import inicio.spring_boot_aula1.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerRepositoryTest {

    @InjectMocks
    private ProducerRepository repository;
    @Mock
    private ProducerData producerData;
    private final List<Producer> producerList = new ArrayList<>();

    @BeforeEach
    void init() {
        var manga = Producer.builder().id(1L).name("Manga").build();
        var arroz = Producer.builder().id(2L).name("Arroz").build();
        var feijao = Producer.builder().id(3L).name("Feijao").build();

        producerList.addAll(List.of(manga, arroz, feijao));
    }

    @Test
    @DisplayName("retonando a lista com todos os producerList")
    @Order(1)
    void findAllReturnAllProducers() {

        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var producers = repository.findAll();

        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producerList);
    }

    @Test
    @DisplayName("find By Id, retornando o producer ")
    @Order(2)
    void findByIdReturnProducer() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expededProducer = producerList.getFirst();

        var producers = repository.findById(expededProducer.getId());
        Assertions.assertThat(producers).isPresent().contains(expededProducer);
    }

    @Test
    @DisplayName("findByName, retornando lista vazia se for nula")
    @Order(3)
    void findByNameReturnProducer() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findByName(null);
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName,se nao for nula")
    @Order(4)
    void findByNameReturnProducerIfNotNull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expededProducer = producerList.getFirst();

        var producers = repository.findByName(expededProducer.getName());
        Assertions.assertThat(producers).contains(expededProducer);
    }

    @Test
    @DisplayName("save creates a producer")
    @Order(5)
    void saveCreatesProducer() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToSave = Producer.builder().id(99L).name("macarrao").createdAt(LocalDateTime.now()).build();
        var producer = repository.save(producerToSave);

        Assertions.assertThat(producer).isEqualTo(producerToSave).hasNoNullFieldsOrProperties();

        var producerSavedOptional = repository.findById(producerToSave.getId());
        Assertions.assertThat(producerSavedOptional).isPresent().contains(producerToSave);
    }

    @Test
    @DisplayName("delet removes a producer")
    @Order(6)
    void findByIdDelet() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToDelet = producerList.getFirst();
        repository.delete(producerToDelet);

        var producer = repository.findAll();

        Assertions.assertThat(producer).isNotEmpty().doesNotContain(producerToDelet);
    }

    @Test
    @DisplayName("aupdate a producer")
    @Order(7)
    void findByIdUpdate() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var producerToUpdate = this.producerList.getFirst();

        Producer.builder().id(3L).name("novo nonome").createdAt(LocalDateTime.now()).build();

        repository.update(producerToUpdate);

        Assertions.assertThat(this.producerList).contains(producerToUpdate);

        var producerUpdatedOptional = repository.findById(producerToUpdate.getId());

        Assertions.assertThat(producerUpdatedOptional).isPresent();
        Assertions.assertThat(producerUpdatedOptional.get().getName()).isEqualTo(producerToUpdate.getName());
    }


}
