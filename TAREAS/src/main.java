import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class main {
    static int j;
    public static void main(String[] args) {

        //Consumer<Integer>pruebaCons=n-> System.out.println(n);
        //BiConsumer<Integer,String>pruebaBiCons=(n,m)-> System.out.println(m + n);
        //pruebaCons.accept(10);
        //pruebaBiCons.accept(10,"hola");

        //Supplier<String>pruebaSupp=()->{ return "Prueba Supplier";};
        //System.out.println(pruebaSupp.get());

        //Function<Integer,String> pruebaFunc= n-> "Prueba Function, número " + n;
        //BiFunction<Integer,Integer,String> pruebaBiFunc = (n,m) -> {return ("Prueba BiFunction" + n + m );};
        //System.out.println(pruebaFunc.apply(1));
        //System.out.println(pruebaBiFunc.apply(5,5));

        //Predicate<Integer> pruebaPre = n -> n > 10;
        //BiPredicate<Integer,Integer> pruebaBiPre = (n, m) -> (n-m) > 10;
        //System.out.println(pruebaPre.test(10));
        //System.out.println(pruebaBiPre.test(20,10));

        //Predicates Datos de entrada y un valor booleano de salida (Bipredicates)
        //Functions Datos de entrada y salida (BiFunction)
        //Supplier No aceptan parámetros pero regresan un valor;
        //Consumer Datos de entrada pero no regresa nada; (Biconsumer)
        //Streams

        List<Integer> list = Arrays.asList(3,5,2,6,8,2,6,9,2,2);
        /*List<Integer> vacio = new ArrayList<>();
        for (int i = 0; i<list.size(); i++){
            if (vacio.contains(list.get(i))){
            }else {
                vacio.add(list.get(i));
                //System.out.println(list.get(i)*list.get(i));
            }
        }
        Collections.sort(vacio);
        for (int i = 0; i<vacio.size(); i++){
            System.out.println(vacio.get(i)*vacio.get(i));
        }*/

        Map<Integer, Integer> map1 = list.stream()
                .map(i->i*i)
                .sorted()
                .distinct()
                .collect(Collectors.toMap(p -> p, p -> p*p));


        map1.forEach((c,v)-> System.out.println(" Clave " + c + " Valor " + v));

        Integer[] a = map1.entrySet().stream()
                .map(Map.Entry::getValue)
                .toArray(Integer[]::new);

        //for (Integer numero : vacio){
        //   if (numero*numero != 4){
        //       System.out.println(vacio);
        //    }
        //}


        //sort
        //map
        //limit
        // USAR SET PARA NO REPETIR ELEMENTOS EN UNA LISTA
        // USAR SORTEDSET PARA ORDENAR LA LISTA

    }

}
