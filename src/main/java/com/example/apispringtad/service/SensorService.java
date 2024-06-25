package com.example.apispringtad.service;

import com.example.apispringtad.dto.SensorDTO;
import com.example.apispringtad.exception.NotFoundException;
import com.example.apispringtad.model.Sensor;
import com.example.apispringtad.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public Sensor create(SensorDTO dto) {
        var sensor = new Sensor();
        BeanUtils.copyProperties(dto, sensor);

        return sensorRepository.save(sensor);
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getById(long id) {
        return sensorRepository.findById(id);
    }

    public Sensor update(long id, SensorDTO dto) throws NotFoundException {
        var res = sensorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Sensor " + id + " não existe.");
        }

        var sensor = res.get();
        sensor.setNome(dto.nome());
        sensor.setStatus(dto.status());

        return sensorRepository.save(sensor);
    }

    public void delete(long id) throws NotFoundException {
        var res = sensorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Sensor " + id + " não existe.");
        }

        sensorRepository.delete(res.get());
    }

}
