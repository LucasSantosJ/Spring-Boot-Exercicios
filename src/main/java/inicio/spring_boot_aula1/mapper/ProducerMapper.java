package inicio.spring_boot_aula1.mapper;

import inicio.spring_boot_aula1.DTO.request.ProducerPostRequest;
import inicio.spring_boot_aula1.domain.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    Producer toProducer(ProducerPostRequest postRequest);
}
