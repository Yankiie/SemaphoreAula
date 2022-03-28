import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        int permissoes = 1;
        Semaphore semaforo = new Semaphore(permissoes);


        for (int idPessoa = 1; idPessoa <= 300; idPessoa++) {
            Thread pessoa = new Conntroller(idPessoa, semaforo);
            pessoa.start();
        }
    }
}