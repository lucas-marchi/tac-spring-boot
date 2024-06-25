package com.example.apispringtad.service;

import com.example.apispringtad.dto.AtuadorDTO;
import com.example.apispringtad.exception.NotFoundException;
import com.example.apispringtad.model.Atuador;
import com.example.apispringtad.repository.AtuadorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtuadorService {

    @Autowired
    private AtuadorRepository atuadorRepository;

    public Atuador create(AtuadorDTO dto) {
        validateAtuador(dto);
        var atuador = new Atuador();

        BeanUtils.copyProperties(dto, atuador);

        return atuadorRepository.save(atuador);
    }

    public List<Atuador> getAll() {
        return atuadorRepository.findAll();
    }

    public Optional<Atuador> getById(long id) {
        return atuadorRepository.findById(id);
    }

    public Atuador update(long id, AtuadorDTO dto) throws NotFoundException {
        var res = atuadorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não existe.");
        }

        var atuador = res.get();
        atuador.setNome(dto.nome());
        atuador.setStatus(dto.status());

        return atuadorRepository.save(atuador);
    }

    public void delete(long id) throws NotFoundException {
        var res = atuadorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não existe.");
        }

        atuadorRepository.delete(res.get());
    }

    private void validateAtuador(AtuadorDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new IllegalArgumentException("Nome do atuador não pode ser vazio.");
        }

    }

}
