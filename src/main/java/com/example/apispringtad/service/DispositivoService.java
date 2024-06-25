package com.example.apispringtad.service;

import com.example.apispringtad.dto.DispositivoDTO;
import com.example.apispringtad.exception.NotFoundException;
import com.example.apispringtad.model.Dispositivo;
import com.example.apispringtad.repository.DispositivoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public Dispositivo create(DispositivoDTO dto) {
        var dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dto, dispositivo);

        return dispositivoRepository.save(dispositivo);
    }

    public List<Dispositivo> getAll() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getById(long id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo update(long id, DispositivoDTO dto) throws NotFoundException {
        var res = dispositivoRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não existe.");
        }

        var dispositivo = res.get();
        dispositivo.setNome(dto.nome());
        dispositivo.setDescricao(dto.descricao());
        dispositivo.setCoordenadas(dto.coordenadas());
        dispositivo.setSensores(dto.sensores());
        dispositivo.setAtuadores(dto.atuadores());

        return dispositivoRepository.save(dispositivo);
    }

    public void delete(long id) throws NotFoundException {
        var res = dispositivoRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não existe.");
        }

        dispositivoRepository.delete(res.get());
    }

}
