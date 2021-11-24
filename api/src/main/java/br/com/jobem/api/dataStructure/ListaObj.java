package br.com.jobem.api.dataStructure;

public class ListaObj<T> {
    private T vetor[];
    private int nroElem = 0;

    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        this.nroElem = 0;
    }

    public Boolean adiciona(T elemento) {
        if (nroElem < vetor.length) {
            vetor[nroElem++] = elemento;
            return true;
        } else {
            System.out.println("A lista está cheia!");
            return false;
        }
    }

    public void exibe() {
        for (int i = 0; i < vetor.length; i++) {
            System.out.println("vetor[" + i + "] = " + vetor[i]);
        }
    }

    public int busca(T elemento) {
        int indice = -1;
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(elemento)) {
                return i;
            }
        }
        return indice;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return false;
        } else {
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            nroElem--;
            return true;
        }
    }

    public boolean removeElemento(T elemento) {
        removePeloIndice(busca(elemento));

        return true;
    }

    public boolean substitui(T antigo, T novo) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(antigo)) {
                vetor[i] = novo;
                return true;
            }
        }
        System.out.println("\nElemento não encontrado!");
        return false;
    }

    public int contaOcorrencias(T elemento) {
        int contagem = 0;
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(elemento)) {
                contagem++;
            }
        }
        return contagem;
    }

    public boolean adicionaNoInicio(T elemento) {
        if (vetor.length == nroElem) {
            System.out.println("\n O Elemento não pôde ser adicionado, pois a lista já está cheia!");
            return false;
        } else {
            nroElem++;
            for (int i = (vetor.length - 1); i >= 0; i--) {

                if (i == 0) {
                    vetor[i] = elemento;
                } else {
                    vetor[i] = vetor[(i - 1)];
                }
            }
            return true;
        }
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void limpa() {
        for (int i = 0; i < nroElem; i++) {
            vetor[i] = null;
        }
        nroElem = 0;
    }
}
