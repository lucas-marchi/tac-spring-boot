package com.example.apispringtad.dto;

import com.example.apispringtad.model.Atuador;
import com.example.apispringtad.model.Sensor;

import java.util.List;

public record DispositivoDTO(String nome, String descricao, List<String> coordenadas, List<Sensor> sensores, List<Atuador> atuadores) {
}
