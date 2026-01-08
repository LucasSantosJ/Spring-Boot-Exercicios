package inicio.spring_boot_aula1.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroesController {

    private static final List<String> HEROIS = List.of("batman", "superman", "flash");

    @GetMapping("/dc")
    public List<String> listAllHerois() {
        return HEROIS;
    }


    @GetMapping("filter")
    public List<String> filtro(@RequestParam(required = false) String name){
        return  HEROIS.stream().filter(herois -> herois.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterlist")
    public List<String> filtro(@RequestParam List<String> name){
        return  HEROIS.stream().filter(name::contains).toList();
    }

    @GetMapping("{name}")
    public String findByname(@PathVariable(required = false) String name) {
        return HEROIS
                .stream()//iniciar fluxo
                .filter(herois -> herois.equalsIgnoreCase(name))
                .findFirst().orElse("");
    }
}

