package br.infnet.edu.requerimentoCompensacao.controller;

import br.infnet.edu.requerimentoCompensacao.Message.Message;
import br.infnet.edu.requerimentoCompensacao.controller.dto.input.AtualizaDto;
import br.infnet.edu.requerimentoCompensacao.controller.dto.input.RequerimentoPensaoInputDto;
import br.infnet.edu.requerimentoCompensacao.controller.dto.output.RequerimentoPensaoOutputDto;
import br.infnet.edu.requerimentoCompensacao.controller.dto.search.RequerimentoPensaoSearchInputDto;
import br.infnet.edu.requerimentoCompensacao.controller.dto.search.RequerimentoPensaoSearchOutputDto;
import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import br.infnet.edu.requerimentoCompensacao.repository.RequerimentoRepository;
import br.infnet.edu.requerimentoCompensacao.service.RequerimentoService;
import br.infnet.edu.requerimentoCompensacao.validator.RequerimentoCustomValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/requerimentos-pensao")
public class RequerimentoPensaoController {
    public static final Logger LOG = LoggerFactory.getLogger(RequerimentoPensaoController.class);

    @Autowired
    RequerimentoService requerimentoService;

    @Autowired
    private RequerimentoRepository requerimentoRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequerimentoPensaoOutputDto> criarRequerimento(
            @Valid @RequestBody RequerimentoPensaoInputDto requerimentoPensaoInputDto,
            UriComponentsBuilder uriBuilder) {

        Requerimento requerimento = requerimentoPensaoInputDto.build();

        requerimentoService.salvar(requerimento);

        URI path = uriBuilder.path("/requerimentos-pensao/{id}")
                .buildAndExpand(requerimento.getIdentificador()).toUri();

        return ResponseEntity.created(path).body(new RequerimentoPensaoOutputDto(requerimento));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequerimentoPensaoOutputDto> consultarRequerimento(@PathVariable Long id) {
        Requerimento requerimento = this.requerimentoRepository.getById(id);
        return ResponseEntity.ok(new RequerimentoPensaoOutputDto(requerimento));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequerimentoPensaoOutputDto> cancelarRequerimento(@PathVariable Long id) {
        requerimentoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH)
    public ResponseEntity<Message> atualizarRequerimento(
            @RequestBody AtualizaDto atualizaDto) {
        Message result;

        AtualizaDto atualiza = atualizaDto.build();
        result = requerimentoService.atualizar(atualiza);
        return ResponseEntity.ok(result);
    }

    @RequestMapping( method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequerimentoPensaoSearchOutputDto> consultarRequerimentos(
            @RequestParam(name = "idSolicitante", required = false) Long idSolicitante,
            @RequestParam(name = "idDestinatario", required = false) Long idDestinatario,
            @RequestParam(name = "situacao", required = false) String situacao) {

        RequerimentoPensaoSearchInputDto requerimentoSearch =
                new RequerimentoPensaoSearchInputDto(idSolicitante, idDestinatario, situacao);

        Specification<Requerimento> requerimentoSearchSpecification = requerimentoSearch.build();
        List<Requerimento> requerimentos = requerimentoRepository.findAll(requerimentoSearchSpecification);

        return RequerimentoPensaoSearchOutputDto.listaDeRequerimentos(requerimentos);
    }

    @InitBinder("requerimentoPensaoInputDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new RequerimentoCustomValidator(this.requerimentoRepository));
    }

}
