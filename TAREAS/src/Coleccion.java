import java.util.*;
import java.util.stream.Collectors;

public class Coleccion {

    // Y la siguiente coleccion
        public static void main(String[] args) {
            List<Curso> cursos = new ArrayList<>();
            cursos.add(new Curso("Cursos profesional de Java", 6.5f, 50, 200 ));
            cursos.add(new Curso("Cursos profesional de Python", 8.5f, 60, 800 ));
            cursos.add(new Curso("Cursos profesional de DB", 4.5f, 70, 700 ));
            cursos.add(new Curso("Cursos profesional de Android", 7.5f, 10, 400 ));
            cursos.add(new Curso("Cursos profesional de Escritura", 1.5f, 10, 300));

            // 1) * Obtener la cantidad de cursos con una duración mayor a 5 horas.
            System.out.println("\n EJERCICIO 1");
            Long count = cursos.stream()
                    .filter(n-> n.getDuracion()>5.0)
                    .count();
            System.out.println(count);


            // 2) * Obtener la cantidad de cursos con una duración menor a 2 horas.
            System.out.println("\n EJERCICIO 2");
            Long count2 = cursos.stream()
                    .filter(n-> n.getDuracion()<2.0)
                    .count();
            System.out.println(count2);


            // 3) * Listar el título de todos aquellos cursos con una cantidad de vídeos mayor a 50.
            System.out.println("\n EJERCICIO 3");
            List<Integer> listVideos = cursos.stream()
                    .filter(n -> n.getVideos()>50).map(n -> n.getVideos())
                    .collect(Collectors.toList());
            System.out.println(listVideos);


            // 4) * Mostrar en consola el título de los 3 cursos con mayor duración.
            System.out.println("\n EJERCICIO 4");
             Comparator<Curso> compareList = Comparator.comparing( n -> n.getDuracion());
             cursos.stream()
                    .sorted(compareList.reversed())
                    .limit(3).forEach(n -> System.out.println(n.getTitulo()));


            // 5) * Mostrar en consola la duración total de todos los cursos.
            System.out.println("\n EJERCICIO 5");
            System.out.println(cursos.stream()
                    .map(n -> n.getDuracion())
                    .mapToDouble(i -> i).sum());


            // 6) * Mostrar en consola todos aquellos cursos que superen el promedio en cuanto a duración se refiere.
            System.out.println("\n EJERCICIO 6");
             OptionalDouble promedio = (cursos.stream().mapToDouble(n -> n.getDuracion()).average());
             System.out.println("Promedio de duracion = " + promedio.getAsDouble());
             cursos.stream()
                     .filter(n -> n.getDuracion() > promedio.getAsDouble())
                     .map(n -> n.getTitulo() + " con duracion: " + n.getDuracion())
                     .forEach(System.out::println);


            // 7) * Mostrar en consola la duración de todos aquellos cursos que tengan una cantidad de alumnos inscritos menor a 500.
            System.out.println("\n EJERCICIO 7");
             cursos.stream()
                     .filter(n -> n.getAlumnos()< 500)
                     .forEach(n -> System.out.println(n.getDuracion()));


            // 8) * Obtener el curso con mayor duración.
            System.out.println("\n EJERCICIO 8");
            Comparator<Curso> compareList2 = Comparator.comparing( n -> n.getDuracion());
            cursos.stream()
                    .sorted(compareList2.reversed())
                    .limit(1)
                    .forEach(n -> System.out.println(n.getTitulo()));


            // 9) * Crear una lista de Strings con todos los titulos de los cursos.
            System.out.println("\n EJERCICIO 9");
             List<String> listaTitulos = cursos.stream()
                     .map(n -> n.getTitulo())
                     .collect(Collectors.toList());
             System.out.println(listaTitulos);
        }
}
