import controller.ServerController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Servidor de Multiprocessamento - Sistema Iniciado\n");
        
        ServerController controller = new ServerController();
        controller.iniciarServidor();
        
        System.out.println("\nSistema Finalizado");
    }
}