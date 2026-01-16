package inicio.spring_boot_aula1.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Anime {

    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<>();

    public Anime(String name, Long id) {
        this.name = name;
        this.id = id;
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

    static {
        var naruto = new Anime("Naruto", 1L);
        var onePiece = new Anime("One Piece", 2L);
        var bleach = new Anime("Bleach", 3L);

        animes.addAll(List.of(naruto, onePiece, bleach));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }

}
