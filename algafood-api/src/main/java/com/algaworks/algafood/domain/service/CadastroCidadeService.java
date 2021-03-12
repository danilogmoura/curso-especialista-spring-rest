package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.excption.EntidadeEmUsoException;
import com.algaworks.algafood.domain.excption.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    private CidadeRepository cidadeRepository;

    private CadastroEstadoService cadastroEstadoService;

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }

    public Cidade cadastrar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
        }
    }

    @Autowired
    public void setCidadeRepository(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Autowired
    public void setCadastroEstadoService(CadastroEstadoService cadastroEstadoService) {
        this.cadastroEstadoService = cadastroEstadoService;
    }
}
