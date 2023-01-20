package com.minsait.services;

import com.minsait.models.Examen;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Datos {

    public static final List<Examen> EXAMANES= Arrays.asList(
            new Examen(1L, "Matematicas"),
            new Examen(2L, "Fisica"),
            new Examen(3L, "Química")
    );

    public static final Examen EXAMEN=new Examen(4L, "Historia");
    public static final List<String> PREGUNTAS=Arrays.asList(
      "Aritmetica",
      "Integrales",
      "Derivadas"
    );
}
