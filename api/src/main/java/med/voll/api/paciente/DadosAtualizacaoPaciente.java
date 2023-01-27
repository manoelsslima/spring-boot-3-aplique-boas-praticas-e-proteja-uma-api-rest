package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        @Embedded
        @Valid
        DadosEndereco endereco) {

}
