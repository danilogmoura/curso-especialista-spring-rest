package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.excption.EntidadeEmUsoException;
import com.algaworks.algafood.domain.excption.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de estado com código %d";
    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

    private EstadoRepository estadoRepository;

    public Estado buscarOuFalhar(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
    }

    public Estado cadastro(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
        }
    }

    @Autowired
    public void setEstadoRepository(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }
}
