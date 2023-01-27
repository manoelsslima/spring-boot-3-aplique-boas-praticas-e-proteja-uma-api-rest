package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import med.voll.api.endereco.Endereco;

public record DadosCadastroPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        @Embedded
        Endereco endereco,
        Boolean ativo) {

}
