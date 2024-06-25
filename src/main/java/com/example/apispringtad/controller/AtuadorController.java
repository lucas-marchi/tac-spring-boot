package com.example.apispringtad.controller;

import com.example.apispringtad.dto.AtuadorDTO;
import com.example.apispringtad.exception.NotFoundException;
import com.example.apispringtad.model.Atuador;
import com.example.apispringtad.service.AtuadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atuador")
public class AtuadorController {

    @Autowired
    private AtuadorService atuadorService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AtuadorDTO dto) {
        try {
            var res = atuadorService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Atuador> getAll() {
        return atuadorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var actuator = atuadorService.getById(id);
        return actuator.isPresent()
                ? ResponseEntity.ok().body(actuator.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody AtuadorDTO dto) {
        try {
            return ResponseEntity.ok().body(atuadorService.update(id, dto));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            atuadorService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
