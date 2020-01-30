package br.com.fiap.cervejaria.controller;

import br.com.fiap.cervejaria.dto.CervejaDTO;
import br.com.fiap.cervejaria.dto.CreateCervejaDTO;
import br.com.fiap.cervejaria.dto.PrecoCervejaDTO;
import br.com.fiap.cervejaria.dto.Tipo;
import br.com.fiap.cervejaria.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cervejas")
public class CervejaController {

    private final CervejaService cervejaService;

    public CervejaController(CervejaService cervejaService) {
        this.cervejaService = cervejaService;
    }

    @GetMapping
    public List<CervejaDTO> getAll(@RequestParam(required = false) Tipo tipo) {
        return cervejaService.findAll(tipo);
    }

    @GetMapping("{id}")
    public CervejaDTO findById(@PathVariable Integer id) {
        return cervejaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CervejaDTO create(@RequestBody @Valid CreateCervejaDTO createCervejaDTO) {
        return cervejaService.create(createCervejaDTO);
    }

    @PutMapping("{id}")
    public CervejaDTO update(@PathVariable Integer id, @RequestBody CreateCervejaDTO createCervejaDTO){
        return cervejaService.update(id, createCervejaDTO);
    }

    @PatchMapping("{id}")
    public CervejaDTO update(@PathVariable Integer id, @RequestBody PrecoCervejaDTO precoCervejaDTO){
        return cervejaService.update(id, precoCervejaDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
      cervejaService.delete(id);
    }

}
