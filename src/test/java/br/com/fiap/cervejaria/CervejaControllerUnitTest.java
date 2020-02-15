package br.com.fiap.cervejaria;

import br.com.fiap.cervejaria.controller.CervejaController;
import br.com.fiap.cervejaria.dto.CervejaDTO;
import br.com.fiap.cervejaria.service.CervejaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CervejaControllerUnitTest {

    @Mock
    CervejaService cervejaService;

    @Test
    public void getAllTest(){
        CervejaController cervejaController = new CervejaController(cervejaService);

        List<CervejaDTO> cervejaDTOList = cervejaController.getAll(null);

        assertEquals(cervejaDTOList.size(), 3);

    }

}
