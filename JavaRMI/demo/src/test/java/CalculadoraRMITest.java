import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.example.Interfaz;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculadoraRMITest {
    private static Interfaz interfaz;
    private static Process servidor;
    private static final int PUERTO = 1100; // Debe coincidir con el del servidor
    private static final String IP = "localhost"; // Asegúrate de que el IP es correcto

    @BeforeAll
    static void iniciarServidor() throws Exception {
        // Iniciar el servidor en un proceso separado
        servidor = new ProcessBuilder("java", "Servidor").start();
        Thread.sleep(3000); // Esperar a que el servidor inicie

        // Conectar al servidor
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        interfaz = (Interfaz) registry.lookup("Calculadora");
    }

    @AfterAll
    static void detenerServidor() {
        if (servidor != null) {
            servidor.destroy();
        }
    }

    @Test
    @Order(1)
    void testSumar() throws RemoteException {
        assertEquals(5.0f, interfaz.sumar(2, 3));
    }

    @Test
    @Order(2)
    void testRestar() throws RemoteException {
        assertEquals(1.0f, interfaz.restar(4, 3));
    }

    @Test
    @Order(3)
    void testMultiplicar() throws RemoteException {
        assertEquals(6.0f, interfaz.multiplicar(2, 3));
    }

    @Test
    @Order(4)
    void testDividir() throws RemoteException {
        assertEquals(2.0f, interfaz.dividir(6, 3));
    }

    @Test
    @Order(5)
    void testDividirPorCero() {
        assertThrows(RemoteException.class, () -> interfaz.dividir(6, 0));
    }

    @Test
    @Order(6)
    void testRaizCuadrada() throws RemoteException {
        assertEquals(4.0f, interfaz.sqrt(16));
    }

    @Test
    @Order(7)
    void testRaizCuadradaNegativa() {
        assertThrows(RemoteException.class, () -> interfaz.sqrt(-4));
    }
}
