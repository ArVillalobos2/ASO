package com.minsait.model;

import com.minsait.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    Cuenta cuenta;

    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeEach
    void setUp(TestInfo testInfo, TestReporter testReporter) {
        this.cuenta=new Cuenta("Ricardo", new BigDecimal(1000));
        testReporter.publishEntry("Iniciando el método");
        this.testInfo=testInfo;
        this.testReporter=testReporter;
        testReporter.publishEntry("Ejecutando: " + testInfo.getTestMethod().get().getName() + " DisplayName: "
        +testInfo.getDisplayName());

    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando el método de prueba");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Iniciando todos los test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando todos los test");
    }
    @Nested
    @DisplayName("Test nombre y saldo")
    class CuentaNombreSaldo {
        @Test
        @DisplayName("Test nombre")
        void testNombreCuenta() {
            String esperado = "Ricardo";
            String real = cuenta.getPersona();

            assertNotNull(real);
            assertEquals(esperado, real);
            assertTrue(real.equals(esperado));
        }

        @Test
        void testSaldoCuenta() {
            assertEquals(1000, cuenta.getSaldo().intValue());
            assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        }

        @Test
        void testReferencia() {
            Cuenta cuenta2 = new Cuenta("Ricardo", new BigDecimal(1000));
            assertEquals(cuenta2, cuenta);
        }
    }

    @Nested
    class CuentaOperacionesTest{
        @Test
        void testRetrirarCuenta(){
            cuenta.retirar(new BigDecimal(100));
            assertNotNull(cuenta.getSaldo());
            assertEquals("900", cuenta.getSaldo().toPlainString());
        }

        @Test
        void testDepositarCuenta(){
            cuenta.depositar(new BigDecimal(100));
            assertNotNull(cuenta.getSaldo());
            assertEquals(1100, cuenta.getSaldo().intValue());
        }

        @Test
        void testTransferirCuentas(){
            Cuenta cuenta2 = new Cuenta("Sebastian", new BigDecimal("50000"));
            Banco banco = new Banco();
            banco.setNombre("BBVA");
            banco.transferir(cuenta2, cuenta, new BigDecimal(10000));

            assertEquals(40000, cuenta2.getSaldo().intValue());
            assertEquals(11000, cuenta.getSaldo().intValue());
        }

    }

    @Test
    void testException(){
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.retirar(new BigDecimal(1500));
        });
        assertAll(
            () -> assertEquals("DineroInsuficiente", exception.getMessage()),
                    () -> assertEquals(DineroInsuficienteException.class, exception.getClass())
    );


    }

    @Test
    @Disabled //Esto es para deshabilitar el test
    void testRelacionBancoCuentas(){
        Cuenta cuenta2 = new Cuenta("Sebastian", new BigDecimal("40000"));
        Banco banco = new Banco();
        banco.addCuenta(cuenta);
        banco.addCuenta(cuenta2);
        banco.setNombre("BBVA");
        banco.transferir(cuenta2, cuenta, new BigDecimal(1000));

        assertAll(
                () -> assertEquals(39000, cuenta2.getSaldo().intValue(),() -> "La cuenta no tiene el saldo deseado"),
                () -> assertEquals(2000, cuenta.getSaldo().intValue()),
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals(banco.getNombre(), cuenta.getBanco().getNombre()),
                () -> assertEquals(banco.getNombre(), cuenta2.getBanco().getNombre()),

                /**
                 * Esperado: Ricardo
                 * Real: A traves del objecto banco van a recuperar el nombre
                 * de la persona sin usar índices.
                 */

                 () -> assertTrue(banco.getCuentas().stream().anyMatch(n -> n.getPersona().equals(cuenta.getPersona())))
        );
    }
}