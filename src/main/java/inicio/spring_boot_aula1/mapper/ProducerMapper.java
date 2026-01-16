package inicio.spring_boot_aula1.mapper;

import inicio.spring_boot_aula1.DTO.request.ProducerPostRequest;
import inicio.spring_boot_aula1.DTO.request.ProducerPutRequest;
import inicio.spring_boot_aula1.DTO.response.ProducerGetResponse;
import inicio.spring_boot_aula1.domain.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    Producer toProducer(ProducerPostRequest postRequest);

    Producer toProducer(ProducerPutRequest request);

    ProducerGetResponse toProducerGetResponse(Producer producer);

    List<ProducerGetResponse> toProducerGetResponseList(List<Producer> producers);
}
