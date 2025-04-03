import Biblioteca.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class ClienteBiblioteca {
    public static void main(String args[]) {
        try {
            // Inicializar el ORB (Object Request Broker)
            ORB orb = ORB.init(args, null);

            // Obtener referencia al servicio de nombres
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Buscar la referencia del objeto (servidor) en el servicio de nombres
            String name = "GestionBiblioteca";
            GestionBiblioteca gestionBiblioteca = GestionBibliotecaHelper.narrow(ncRef.resolve_str(name));


            // Usar la interfaz para llamar a las operaciones del servidor
            // Ejemplo: Buscar un libro
            String tituloLibro = "El principito";
            Libro libro = gestionBiblioteca.buscarLibro(tituloLibro);
            System.out.println("Libro encontrado: " + libro.titulo + ", " + libro.autor + ", ISBN: " + libro.ISBN);

            // Ejemplo: Prestar un libro
            boolean resultadoPrestamo = gestionBiblioteca.prestarLibro(libro.ISBN);
            if (resultadoPrestamo) {
                System.out.println("Libro prestado con éxito.");
            } else {
                System.out.println("El libro no está disponible para préstamo.");
            }

            // Agregar un libro nuevo
            Libro nuevoLibro = new Libro("El señor de los añillos", "JRR tolkien", "5678", true);
            boolean agregado = gestionBiblioteca.agregarLibro(nuevoLibro);
            if (agregado) {
                System.out.println("Libro agregado correctamente.");
            } else {
                System.out.println("El libro ya existe.");
            }

            // Listar todos los libros
            Libro[] listaLibros = gestionBiblioteca.listarLibros();
            System.out.println("Lista de libros en la biblioteca:");
            for (Libro libroLista : listaLibros) {
                System.out.println(libroLista.titulo + " - " + libroLista.autor+ "esta disponible: " + libroLista.estaDisponible);
            }

            // Listar libros por autor
            String autor = "JRR tolkien";
            listaLibros = gestionBiblioteca.buscarPorAutor(autor);
            System.out.println("Lista de libros del autor :"+ autor);
            for (Libro libroLista : listaLibros) {
                System.out.println(libroLista.titulo + "\n" );
            }
            /* Funciona pero no quiero ejecutar cada vez
            // Eliminar un libro por ISBN
            String isbnEliminar = "5678"; // ISBN del libro a eliminar
            boolean eliminado = gestionBiblioteca.eliminarLibro(isbnEliminar);

            if (eliminado) {
                System.out.println("Libro eliminado con éxito.");
            } else {
                System.out.println("No se encontró un libro con ese ISBN.");
            }
            */



            // Continuar con más operaciones según sea necesario...

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

