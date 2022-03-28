import java.util.concurrent.Semaphore;

public class Conntroller extends Thread {

    private int idPessoa;
    private Semaphore semaforo;
    private static int ingresso = 100;

    public Conntroller(int idPessoa, Semaphore semaforo) {
        this.idPessoa = idPessoa;
        this.semaforo = semaforo;
    }

    public void run() {
        login();
    }

    private void login() {
        System.out.println("Pessoa " + idPessoa + " Efetue seu login");
        System.out.println("Pessoa " + idPessoa + " Digite seu úsuario : ");
        System.out.println("Pessoa " + idPessoa + " Digite sua senha : ");
        try {
            int tempo = (int) (Math.random() * 1950) + 51;
            System.out.println("Pessoa " + idPessoa + " Aguade a autentificação...");
            sleep(tempo);
            if (tempo >= 1000) {
                System.out.println("Pessoa " + idPessoa + "Login realizado com sucesso, prossiga para a proxíma etapa");
                compra();
            } else {
                System.out.println("Pessoa " + idPessoa
                        + " Erro de carregamento \n Não será possivel realizar a compra do ingresso");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void compra() {
        System.out.println("Pessoa " + idPessoa + " Bem-vindo ao processo de compra do show ");
        try {
            int tempo = (int) (Math.random() * 3001) + 1001;
            System.out.println("Aguarde a autentificação de compra");
            sleep(tempo);
            if (tempo <= 2500) {
                System.out.println("Autentificação autorizada ");
                finalizar();
            } else {
                System.out.println(
                        "Pessoa " + idPessoa + " Seu tempo de sessão expirou, e não será possivel realizar a compra");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void finalizar() {
        try {
            semaforo.acquire();
            int compras = (int) (Math.random() * 4) + 1;
            if (compras <= ingresso) {
                System.out.println("Parabens Pessoa" + idPessoa + " Você comprou " + compras + " ingressos");
                ingresso -= compras;
                System.out.println("Ainda restam " + ingresso + " ingressos");
            } else {
                System.out.println(
                        "Infelizmente não temos esse tanto de ingressos disponivel. Não será possivel realizar a compra ");
            }
            if(ingresso == 0) {
                System.out.println("OS ingressos acabaram");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }

    }

}