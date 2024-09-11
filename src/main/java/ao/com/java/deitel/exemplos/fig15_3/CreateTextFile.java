package ao.com.java.deitel.exemplos.fig15_3;


import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile
{
    private static Formatter output;
    private static Scanner input;

    public static void main(String[] args)
    {
        openFile();
        addRecords();
        closeFile();
    }

    // Abre o arquivo clients.txt
    public static void openFile() {
        try
        {
            output = new Formatter("clients.txt"); // Abre o arquivo
        }
        catch (SecurityException securityException)
        {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1); // Termina o programa
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file. Terminating.");
            System.exit(1); // Termina o programa
        }
    }

    // Adiciona registros ao arquivo
    public static void addRecords() {
        input = new Scanner(System.in);
        System.out.printf("%s%n%s%n? ",
                "Enter account number, first name, last name and balance.",
                "Enter end-of-file indicator (Ctrl+D on UNIX/Linux/macOS or Ctrl+Z on Windows) to end input.");

        while (input.hasNext())
        {
            try
            {
                // Gera saída do novo registro para o arquivo; supõe entrada válida
                output.format("%d %s %s %.2f%n", input.nextInt(),
                        input.next(), input.next(), input.nextDouble());
            }
            catch (FormatterClosedException formatterClosedException)
            {
                System.err.println("Error writing to file. Terminating.");
                break;
            }
            catch (NoSuchElementException elementException)
            {
                System.err.println("Invalid input. Please try again.");
                input.nextLine(); // Descarta entrada para o usuário tentar de novo
            }

            System.out.print("? ");
        }
    }

    // Fecha o arquivo clients.txt e fecha o Scanner de entrada
    public static void closeFile()
    {
        if (output != null) {
            output.close();
        }
        if (input != null) {
            input.close(); // Fecha o Scanner
        }
    }
}