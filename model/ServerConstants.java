package model;

public class ServerConstants {
    public static final int TOTAL_THREADS = 21;
    public static final int TIPO_UM = 1;
    public static final int TIPO_DOIS = 2;
    public static final int TIPO_ZERO = 0;
    
    // Tempos em milissegundos
    public static final int BD_TRANSACAO_TIPO_1 = 1000;
    public static final int BD_TRANSACAO_TIPO_2_3 = 1500;
    
    private ServerConstants() {
        // Classe de constantes, não deve ser instanciada
    }
}