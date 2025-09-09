package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;
import view.ServerView;

public class ServerController {
    private final Semaphore databaseSemaphore;
    private final Random random;
    private final ServerView view;
    
    public ServerController() {
        this.databaseSemaphore = new Semaphore(1);
        this.random = new Random();
        this.view = new ServerView();
    }
    
    public void iniciarServidor() {
        view.mostrarCabecalho();
        view.mostrarConfiguracao(21);
        
        for (int threadId = 1; threadId <= 21; threadId++) {
            final int currentThreadId = threadId;
            
            new Thread(() -> {
                try {
                    processarThread(currentThreadId);
                } catch (InterruptedException e) {
                    view.mostrarErro(currentThreadId, e.getMessage());
                }
            }).start();
            
            aguardar(30);
        }
    }
    
    private void processarThread(int threadId) throws InterruptedException {
        view.mostrarInicioThread(threadId);
        
        int threadType = threadId % 3;
        
        switch (threadType) {
            case 1 -> executarTipoUm(threadId);
            case 2 -> executarTipoDois(threadId);
            case 0 -> executarTipoZero(threadId);
        }
        
        view.mostrarFinalizacaoThread(threadId);
    }
    
    private void executarTipoUm(int threadId) throws InterruptedException {
        executarCalculos(threadId, 0.2, 1.0, "Cálculos Iniciais");
        executarTransacaoBancoDados(threadId, 1000, "Transação Primária");
        executarCalculos(threadId, 0.2, 1.0, "Cálculos Finais");
        executarTransacaoBancoDados(threadId, 1000, "Transação Secundária");
    }
    
    private void executarTipoDois(int threadId) throws InterruptedException {
        for (int etapa = 1; etapa <= 3; etapa++) {
            executarCalculos(threadId, 0.5, 1.5, "Cálculos " + etapa);
            executarTransacaoBancoDados(threadId, 1500, "Transação " + etapa);
        }
    }
    
    private void executarTipoZero(int threadId) throws InterruptedException {
        for (int etapa = 1; etapa <= 3; etapa++) {
            executarCalculos(threadId, 1.0, 2.0, "Cálculos " + etapa);
            executarTransacaoBancoDados(threadId, 1500, "Transação " + etapa);
        }
    }
    
    private void executarCalculos(int threadId, double min, double max, String descricao) 
            throws InterruptedException {
        double tempoSegundos = min + random.nextDouble() * (max - min);
        view.mostrarCalculos(threadId, descricao, tempoSegundos);
        aguardar((int)(tempoSegundos * 1000));
    }
    
    private void executarTransacaoBancoDados(int threadId, int milissegundos, String descricao) 
            throws InterruptedException {
        view.mostrarAguardandoBD(threadId);
        
        databaseSemaphore.acquire();
        try {
            view.mostrarTransacaoBD(threadId, descricao, milissegundos / 1000.0);
            aguardar(milissegundos);
        } finally {
            databaseSemaphore.release();
            view.mostrarLiberacaoBD(threadId);
        }
    }
    
    private void aguardar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}