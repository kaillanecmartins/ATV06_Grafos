package Util;

public class Lista {
    private Node head;

    private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public Lista() {
        head = null;
    }

    public void adicionar(Object data) {
        Node novoNode = new Node(data);
        novoNode.next = head;
        head = novoNode;
    }

    public boolean contem(Object data) {
        Node atual = head;
        while (atual != null) {
            if (atual.data.equals(data)) {
                return true;
            }
            atual = atual.next;
        }
        return false;
    }

    public void remover(Object data) {
        if (head == null) return;
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node atual = head;
        while (atual.next != null) {
            if (atual.next.data.equals(data)) {
                atual.next = atual.next.next;
                return;
            }
            atual = atual.next;
        }
    }

    public Object[] obterTodos() {
        int tamanho = 0;
        Node atual = head;
        while (atual != null) {
            tamanho++;
            atual = atual.next;
        }

        Object[] elementos = new Object[tamanho];
        atual = head;
        int index = 0;
        while (atual != null) {
            elementos[index++] = atual.data;
            atual = atual.next;
        }
        return elementos;
    }
}
