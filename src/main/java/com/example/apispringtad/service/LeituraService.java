package com.example.apispringtad.service;

import com.example.apispringtad.dto.LeituraDTO;
import com.example.apispringtad.exception.NotFoundException;
import com.example.apispringtad.model.Leitura;
import com.example.apispringtad.repository.LeituraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository leituraRepository;

    public Leitura create(LeituraDTO dto) {
        var leitura = new Leitura();
        BeanUtils.copyProperties(dto, leitura);

        return leituraRepository.save(leitura);
    }

    public List<Leitura> getAll() {
        return leituraRepository.findAll();
    }

    public Optional<Leitura> getById(long id) {
        return leituraRepository.findById(id);
    }

    public Leitura update(long id, LeituraDTO dto) throws NotFoundException {
        var res = leituraRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Leitura " + id + " não existe.");
        }

        var leitura = res.get();
        leitura.setSensor(dto.sensor());
        leitura.setValor(dto.valor());
        leitura.setDataHora(dto.dataHora());

        return leituraRepository.save(leitura);
    }

    public void delete(long id) throws NotFoundException {
        var res = leituraRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Leitura " + id + " não existe.");
        }

        leituraRepository.delete(res.get());
    }

}
