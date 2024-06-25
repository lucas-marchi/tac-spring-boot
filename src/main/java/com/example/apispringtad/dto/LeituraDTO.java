package com.example.apispringtad.dto;

import com.example.apispringtad.model.Sensor;

import java.time.LocalDateTime;

public record LeituraDTO(Sensor sensor, String valor, LocalDateTime dataHora) {
}
