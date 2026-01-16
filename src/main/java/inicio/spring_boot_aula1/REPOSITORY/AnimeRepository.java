package inicio.spring_boot_aula1.REPOSITORY;

import inicio.spring_boot_aula1.domain.Anime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimeRepository {

    private static final List<Anime> ANIMES = new ArrayList<>();

    static {
        var naruto = new Anime("Naruto", 1L);
        var onePiece = new Anime("One Piece", 2L);
        var bleach = new Anime("Bleach", 3L);

        ANIMES.addAll(List.of(naruto, onePiece, bleach));
    }

    public Optional<Anime> findById(String id) {
        return  ANIMES.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List <Anime> findAll() {
        return ANIMES;
    }

    public  List<Anime> findByName(String name) {
        if(name.isEmpty()){
            return ANIMES;
        }
        return ANIMES.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }


}
