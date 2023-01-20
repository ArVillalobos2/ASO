package com.minsait.services;

import com.minsait.models.Examen;
import com.minsait.repositories.ExamenRepository;
import com.minsait.repositories.PreguntaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExamenServiceImpl implements ExamenService{

    private ExamenRepository examenRepository;
    private PreguntaRepository preguntaRepository;
    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return examenRepository.findAll().stream()
                .filter(n -> n.getNombre().equals(nombre))
                .findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = findExamenPorNombre(nombre);
        Examen examen=null;
        if(examenOptional.isPresent()){
            examen=examenOptional.get();
            List<String> preguntas=preguntaRepository.findPreguntasByExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }

    @Override
    public Examen save(Examen examen) {

        if(!examen.getPreguntas().isEmpty())
        preguntaRepository.savePreguntas(examen.getPreguntas());
        return examenRepository.save(examen);
    }
}
