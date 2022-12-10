package br.infnet.edu.requerimentoCompensacao.validator;

import br.infnet.edu.requerimentoCompensacao.controller.dto.input.DependenteInputDto;
import br.infnet.edu.requerimentoCompensacao.controller.dto.input.RequerimentoPensaoInputDto;
import br.infnet.edu.requerimentoCompensacao.enums.EnumEstadoCivil;
import br.infnet.edu.requerimentoCompensacao.enums.EnumVinculo;
import br.infnet.edu.requerimentoCompensacao.repository.RequerimentoRepository;
import lombok.SneakyThrows;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RequerimentoCustomValidator implements Validator {


    private final RequerimentoRepository requerimentoRepository;

    public RequerimentoCustomValidator(RequerimentoRepository requerimentoRepository) {
        this.requerimentoRepository = requerimentoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RequerimentoPensaoInputDto.class.isAssignableFrom(clazz);
    }


    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        String DATA_REFERENCIA = "19881005";
        RequerimentoPensaoInputDto requerimento = (RequerimentoPensaoInputDto) target;

        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dataReq = format.parse(requerimento.getDataInicioBeneficioPensao());
        Date dataRef = format.parse(DATA_REFERENCIA);


        // rule one
        if (dataReq.before(dataRef)) {
            errors.reject("message.requerimentoPensaoInputDto.dataInicioBeneficio",
                    null, "A data de início de benefício deve ser maior ou igual a 05/10/1988");
        }

        // rule two
        if (requerimento.getDependentes() != null && requerimento.getDependentes().size() > 0) {
            if (!verificarVinculosValidos(requerimento.getDependentes())) {
                errors.reject("message.requerimentoPensaoInputDto.vinculoInvalido",
                        null, "O dependente com vínculo de Filho e Menor sob guarda deve possuir estado civil igual a Solteiro");
            }
        }

        // rule three
        int totCpfBeneficiario = requerimentoRepository.countCpfBeneficiario(requerimento.getCpfBeneficiario());
        int totIdSolicitante = requerimentoRepository.countIdSolicitante(requerimento.getSolicitante());
        int totIdDestinatario = requerimentoRepository.countIdDestinatario(requerimento.getDestinatario());

        if (totCpfBeneficiario > 0 || totIdDestinatario >0  || totIdSolicitante >0){
            errors.reject("message.requerimentoPensaoInputDto.requerimentoExcedido",
                    null, "É permitido apenas um requerimento para o mesmo CPF Beneficiario, solicitante ou destinatário");
        }


    }

    private boolean verificarVinculosValidos(List<DependenteInputDto> dependentes) {
        for (DependenteInputDto dependente : dependentes) {
            if (((EnumVinculo.getVinculo(dependente.getVinculo()).equals(EnumVinculo.FILHO))
                    && !(EnumEstadoCivil.getEstadoCivil(dependente.getEstadoCivil()).equals(EnumEstadoCivil.SOLTEIRO)) ||

                    (EnumVinculo.getVinculo(dependente.getVinculo()).equals(EnumVinculo.FILHO_ADOTIVO_MENOR_GUARDA)
                            && !(EnumEstadoCivil.getEstadoCivil(dependente.getEstadoCivil()).equals(EnumEstadoCivil.SOLTEIRO))))) {
                return false;
            }
        }
        return true;
    }
}
