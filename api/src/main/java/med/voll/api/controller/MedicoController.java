package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    /**
     * Use <strong>size</strong> to set the return size. Ex.: size=10 (10 records)<br>
     * Use <strong>page</strong> to set the page (default is 0). Ex.: page=1<br>
     * Use <strong>sort</strong> to order the result (sort=fieldname). Ex.: sort=name<br>
     * Use <strong>,desc</strong> to do a desc sorting (default is asc). Ex.: sort=name,desc<br>
     * You can ommit @PageableDefault but it can make the API loses performance
     * @param paginacao informações da paginação (size, page, sort)
     * @return Lista de dados dos médicos
     */
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 1, page = 0, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = this.repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = this.repository.getReferenceById(id);
        medico.excluir();
    }
}
