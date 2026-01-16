package inicio.spring_boot_aula1.controller;

import inicio.spring_boot_aula1.domain.Anime;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/v1/animes")
public class AnimeController {

    @GetMapping()
    public List<Anime> Animes(){
        return Anime.getAnimes();
    }

    @GetMapping("nome")
    public List<Anime> listName(@RequestParam (required = false) String name) {
        var animes = Anime.getAnimes();
        if (animes.isEmpty()) {return animes;}
        return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.getAnimes()
                .stream().filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Anime save(@RequestBody Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(10_000));
        Anime.getAnimes().add(anime);
        return anime;
    }

}
