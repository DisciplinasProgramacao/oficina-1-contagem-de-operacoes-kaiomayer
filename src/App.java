import java.util.Random;

public class App {
    static int[] tamanhosTesteGrande =  { 125_000, 250_000, 500_000, 1_000_000, 2_000_000 };
    static int[] tamanhosTesteMedio =   {  12_500,  25_000,  50_000,   100_000,   200_000 };
    static int[] tamanhosTestePequeno = {       3,       6,      12,        24,        48 };
    static Random aleatorio = new Random(42);

    static int operacoes;

    static int codigo1(int[] vetor) {
        operacoes = 0;
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i]%2;
            resposta += vetor[i] % 2;
            operacoes++;
        }
        return resposta;
    }

    static int codigo2(int[] vetor) {
        operacoes = 0;
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
                operacoes++;
            }
            operacoes++;
        }
        return contador;
    }

    static int codigo3(int[] vetor) {
        operacoes = 0;
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[menor]) {
                    menor = j;
                }
                operacoes++;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
            operacoes += 3;
        }
        return operacoes;
    }

    static int codigo4(int n) {
        operacoes++;
        if (n <= 2) {
            return 1;
        } else {
            return codigo4(n - 1) + codigo4(n - 2);
        }
    }

    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho / 2);
        }
        return vetor;
    }

    public static void main(String[] args) {
        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            long tempoInicial = System.currentTimeMillis();
            int operacoes1 = codigo1(vetor);
            long tempoFinal = System.currentTimeMillis();
            System.out.println("Código 1 - Tamanho: " + tamanho + ", Operações: " + operacoes1 + ", Tempo: " + (tempoFinal - tempoInicial) + "ms");
        }

        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            long tempoInicial = System.currentTimeMillis();
            int operacoes2 = codigo2(vetor);
            long tempoFinal = System.currentTimeMillis();
            System.out.println("Código 2 - Tamanho: " + tamanho + ", Operações: " + operacoes2 + ", Tempo: " + (tempoFinal - tempoInicial) + "ms");
        }

        // Código 3: Teste Médio
        for (int tamanho : tamanhosTesteMedio) {
            int[] vetor = gerarVetor(tamanho);
            long tempoInicial = System.currentTimeMillis();
            long operacoes3 = codigo3(vetor);
            long tempoFinal = System.currentTimeMillis();
            System.out.println("Código 3 - Tamanho: " + tamanho + ", Operações: " + operacoes3 + ", Tempo: " + (tempoFinal - tempoInicial) + "ms");
        }

        // Código 4: Teste Pequeno
        for (int n : tamanhosTestePequeno) {
            operacoes = 0;
            long tempoInicial = System.currentTimeMillis();
            codigo4(n);
            long tempoFinal = System.currentTimeMillis();
            System.out.println("Código 4 - n: " + n + ", Operações: " + operacoes + ", Tempo: " + (tempoFinal - tempoInicial) + "ms");
        }
    }
}

