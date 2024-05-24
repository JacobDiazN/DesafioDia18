import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> lista = new ArrayList<>(Arrays.asList("Perro", "Gato", "Juan", "Daniel", "Juan", "Gato", "Perro", "Camila", "Daniel", "Camila"));

        String dir = "src/directorio";
        String arch = "/mi_archivo.txt";
        crearArchivo(dir, arch);
        escribirArchivo(dir + arch, lista);
        mostrarMenu(dir, arch);

    }

    public static void mostrarMenu(String dir, String arch) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n============ Menú ============");
            System.out.println("1. Buscar texto en el archivo");
            System.out.println("2. Mostrar contenido del archivo");
            System.out.println("3. Salir");
            System.out.println("Elige una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número.");
                opcion = -1; // Asignamos -1 para volver al bucle del menú
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa el texto a buscar:");
                    String textoABuscar = sc.nextLine();
                    buscarTexto(dir + "/" + arch, textoABuscar);
                    break;
                case 2:
                    listarContenido(dir + "/" + arch);
                    break;
                case 3:
                    System.out.println("Gracias por usar este programa!!!!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 3);
    }

    public static void crearArchivo(String nombreDirectorio, String nombreArchivo){
        File dir = new File(nombreDirectorio);
        if (dir.mkdir()){
            System.out.println("__________________________________________________________________");
            System.out.printf("Se a creado el directorio '%s' con éxito.\n", dir.getName());
        }else if(dir.exists()){
            System.out.println("__________________________________________________________________");
            System.out.printf("El directorio '%s' ya existe\n", dir.getName());
        }else{
            System.out.println("Error al crear directorio");
        }

        File arch = new File(dir, nombreArchivo);
        try {
            if (arch.exists()){
                System.out.printf("El archivo '%s' ya existe\n", arch.getName());
                System.out.println("__________________________________________________________________");
            }else{
                if (arch.createNewFile()){
                    System.out.printf("Se ha creado el archivo '%s'," +
                            " dentro de la carpeta '%s', con éxito\n", arch.getName(), dir.getName());
                    System.out.println("__________________________________________________________________");
                }
            }
        } catch (IOException e) {
            System.out.println("ocurrio un error");
            e.printStackTrace();
        }
    }

    public static void escribirArchivo(String nombreArchivo, ArrayList<String> lista) {
        File f = new File(nombreArchivo);
        try{
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            Iterator<String> iterator = lista.iterator();
            while (iterator.hasNext()) {
                String elemento = iterator.next();
                bw.write(elemento);
                bw.newLine();
                System.out.printf("Aqui se agregró el siguiente elemento '%-6s' en esta dirección '%s'\n", elemento, nombreArchivo);
            }
            /*for (String elemento : lista) {
                bw.write(elemento);
                bw.newLine();
                System.out.printf("Aqui se agregró el siguiente elemento '%-6s' en esta dirección '%s'\n", elemento, nombreArchivo);
            }*/
            System.out.println("________________________________________________________________________");
            System.out.printf("Se ha escrito EXITÓSAMENTE en el archivo '%s'\n", nombreArchivo);
            System.out.println("________________________________________________________________________");
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo");
            e.printStackTrace();
        }
    }

    public static void buscarTexto(String nombreArchivo, String texto) {
        int count = 0;
        File archivo = new File(nombreArchivo);
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                int siguiente = 0;
                while ((siguiente = linea.indexOf(texto, siguiente)) != -1) {
                    count++;
                    siguiente += texto.length();
                }
            }
            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo");
            e.printStackTrace();
        }

        if (count > 0) {
            String veces = (count == 1) ? "vez" : "veces";
            System.out.printf("El texto '%s' se encuentra %d %s en el archivo '%s'.\n", texto, count, veces, nombreArchivo);
        } else {
            System.out.printf("El texto '%s' no fue encontrado en el archivo '%s'.\n", texto, nombreArchivo);
        }
    }

    public static void listarContenido(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            System.out.println("\n_______________________________________________________________________________");
            System.out.println("Contenido del archivo '" + nombreArchivo + "':");
            System.out.println("_______________________________________________________________________________");
            while ((linea = br.readLine()) != null) {
                System.out.printf("[" + linea + "] ");
            }
            System.out.println("\n_______________________________________________________________________________");
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo");
            e.printStackTrace();
        }
    }

}