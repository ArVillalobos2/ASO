package com.minsait.services;

import com.minsait.models.Examen;
import com.minsait.repositories.ExamenRepository;
import com.minsait.repositories.PreguntaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {
    @Mock
    ExamenRepository examenRepository;

    @Mock
    PreguntaRepository preguntaRepository;

    @InjectMocks
    ExamenServiceImpl service;
    @Captor
    ArgumentCaptor<Long> captor;
    @Test
    void testArgumentCaptor(){
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMANES);
        Mockito.when(preguntaRepository.findPreguntasByExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        service.findExamenPorNombreConPreguntas("Matematicas");
        verify(preguntaRepository).findPreguntasByExamenId(captor.capture());
        assertEquals(1L, captor.getValue());
    }
    @Test
    void testFindExamenPorNombre(){
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMANES);

        Optional<Examen> examen=service.findExamenPorNombre("Fisica");
        assertTrue(examen.isPresent());
        assertEquals(2L, examen.get().getId());
    }

    @Test
    void testFindExamenPorNombreConPreguntas(){
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMANES);
        Mockito.when(preguntaRepository.findPreguntasByExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        Examen examen = service.findExamenPorNombreConPreguntas("Fisica");

        assertTrue(examen.getPreguntas().contains("Aritmetica"));
        verify(examenRepository).findAll();
        verify(preguntaRepository).findPreguntasByExamenId(2L);
    }

    @Test
    void textException(){
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMANES);
        Mockito.when(preguntaRepository.findPreguntasByExamenId(anyLong())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> service.findExamenPorNombreConPreguntas("Fisica"));
        assertEquals(IllegalArgumentException.class, assertThrows(IllegalArgumentException.class, () -> {
                    service.findExamenPorNombreConPreguntas("Fisica");
                }).getClass());

        // Validar con JUNIT que me regresa una Exception de tipo illegalArgumentException
    }

    @Test
    void testDoThrow(){
        //Given
        doThrow(RuntimeException.class).when(preguntaRepository).savePreguntas(anyList());

        //When
        Examen examen = Datos.EXAMEN;
        examen.setPreguntas(Datos.PREGUNTAS);

        //Then
        assertThrows(RuntimeException.class, () -> service.save(examen));

    }

    @Test
    void testDoAnswer(){
        Mockito.when(examenRepository.findAll()).thenReturn(Datos.EXAMANES);
        doAnswer(invocationOnMock -> {
            Long id= invocationOnMock.getArgument(0);
            return id == 1L?Datos.PREGUNTAS:Collections.EMPTY_LIST;
        }).when(preguntaRepository).findPreguntasByExamenId(anyLong());
        //Mockito.when(preguntaRepository.findPreguntasByExamenId(1L)).thenReturn(Datos.PREGUNTAS);
        //Mockito.when(preguntaRepository.findPreguntasByExamenId(anyLong())).thenReturn(Collections.EMPTY_LIST);

        Examen examen=service.findExamenPorNombreConPreguntas("Matematicas");

        assertAll(
                () -> assertEquals(1L, examen.getId(), () -> "El examen no es mate"),
                () -> assertFalse(examen.getPreguntas().isEmpty(), () -> "El examen no es mate")
                //() -> assertTrue(examen.getPreguntas().isEmpty(), () -> "El examen es mate")
        );
    }

    @Test
    void testSaveExamen(){
        Examen examen = new Examen(null, "Ingles");
        examen.setPreguntas(Datos.PREGUNTAS);
        Mockito.when(examenRepository.save(any())).then(invocationOnMock -> {
            Examen examen1 = invocationOnMock.getArgument(0);
            examen1.setId(5L);
            return examen1;
        });

        Examen examen1 = service.save(examen);

        assertEquals(5L,examen1.getId());
        assertEquals("Ingles",examen1.getNombre());
        assertEquals(3,examen1.getPreguntas().size());
        verify(preguntaRepository, atLeastOnce()).savePreguntas(anyList());


    }

}