package br.com.fiap.cervejaria.service.impl;

import br.com.fiap.cervejaria.dto.CervejaDTO;
import br.com.fiap.cervejaria.dto.CreateCervejaDTO;
import br.com.fiap.cervejaria.dto.PrecoCervejaDTO;
import br.com.fiap.cervejaria.dto.Tipo;
import br.com.fiap.cervejaria.entity.Cerveja;
import br.com.fiap.cervejaria.repository.CervejaRepository;
import br.com.fiap.cervejaria.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CervejaServiceImpl implements CervejaService {

    private final CervejaRepository cervejaRepository;

    public CervejaServiceImpl(CervejaRepository cervejaRepository) {
        this.cervejaRepository = cervejaRepository;
    }

    @Override
    public List<CervejaDTO> findAll(Tipo tipo) {
        if(tipo != null)
        return cervejaRepository.findAllByTipo(tipo).stream().map(CervejaDTO::new).collect(Collectors.toList());
        else
            return cervejaRepository.findAll().stream().map(CervejaDTO::new).collect(Collectors.toList());
    }
    @Override
    public CervejaDTO findById(Integer id) {
        return cervejaRepository.findById(id).map(CervejaDTO::new).get();
    }

    @Override
    public CervejaDTO create(CreateCervejaDTO createCervejaDTO) {
        Cerveja cerveja = new Cerveja(createCervejaDTO);

        CervejaDTO cervejaDTO = saveAndGetCervejaDTO(cerveja);
        return cervejaDTO;
    }

    @Override
    public CervejaDTO update(Integer id, CreateCervejaDTO createCervejaDTO) {
        Cerveja cerveja = new Cerveja();
        cerveja.setId(id);
        cerveja.setDataLancamento(createCervejaDTO.getDataLancamento());
        cerveja.setMarca(createCervejaDTO.getMarca());
        cerveja.setPreco(createCervejaDTO.getPreco());
        cerveja.setTeorAlcoolico(createCervejaDTO.getTeorAlcoolico());
        cerveja.setTipo(createCervejaDTO.getTipo());

        CervejaDTO cervejaDTO = saveAndGetCervejaDTO(cerveja);
        return cervejaDTO;
    }

    @Override
    public CervejaDTO update(Integer id, PrecoCervejaDTO precoCervejaDTO) {

        Cerveja cerveja = cervejaRepository.findById(id).get();
        cerveja.setPreco(precoCervejaDTO.getPreco());

        CervejaDTO cervejaDTO = saveAndGetCervejaDTO(cerveja);
        return cervejaDTO;
    }

    private CervejaDTO saveAndGetCervejaDTO(Cerveja cerveja) {
        Cerveja savedCerveja = cervejaRepository.save(cerveja);
        return new CervejaDTO(savedCerveja);
    }

    @Override
    public void delete(Integer id) {
        cervejaRepository.deleteById(id);

    }
}
