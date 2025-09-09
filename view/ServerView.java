package view;

public class ServerView {
    
    public void mostrarCabecalho() {
        System.out.println("SERVIDOR DE MULTIPROCESSAMENTO");
        System.out.println("==========================================");
        System.out.println("Sistema com controle de concorrência e");
        System.out.println("gerenciamento de recursos compartilhados");
        System.out.println("==========================================\n");
    }
    
    public void mostrarConfiguracao(int totalThreads) {
        System.out.println("CONFIGURAÇÃO DO SISTEMA");
        System.out.println("Total de Threads: " + totalThreads);
        System.out.println("Núcleos de Processamento: Múltiplos");
        System.out.println("Recurso Compartilhado: Banco de Dados");
        System.out.println("Política de Acesso: Exclusivo Mutuamente");
        System.out.println("------------------------------------------\n");
    }
    
    public void mostrarInicioThread(int threadId) {
        System.out.println("THREAD " + threadId + " [TIPO " + (threadId % 3) + "] - INICIADA");
    }
    
    public void mostrarCalculos(int threadId, String descricao, double tempo) {
        System.out.println("Thread " + threadId + " | " + descricao + 
                         " | Tempo: " + String.format("%.2fs", tempo));
    }
    
    public void mostrarAguardandoBD(int threadId) {
        System.out.println("Thread " + threadId + " | Aguardando liberação do Banco de Dados...");
    }
    
    public void mostrarTransacaoBD(int threadId, String descricao, double tempo) {
        System.out.println("Thread " + threadId + " | [BANCO DE DADOS] " + descricao + 
                         " | Tempo: " + String.format("%.1fs", tempo));
    }
    
    public void mostrarLiberacaoBD(int threadId) {
        System.out.println("Thread " + threadId + " | Recurso do Banco de Dados liberado");
    }
    
    public void mostrarFinalizacaoThread(int threadId) {
        System.out.println("THREAD " + threadId + " - PROCESSAMENTO CONCLUÍDO\n");
    }
    
    public void mostrarErro(int threadId, String mensagem) {
        System.out.println("ERRO na Thread " + threadId + ": " + mensagem);
    }
    
    public void mostrarEstatisticas() {
        System.out.println("ESTATÍSTICAS DE EXECUÇÃO");
        System.out.println("• Threads processadas: 21");
        System.out.println("• Tipos diferentes: 3");
        System.out.println("• Transações BD: 45");
        System.out.println("• Tempo total: Variável conforme execução");
    }
}