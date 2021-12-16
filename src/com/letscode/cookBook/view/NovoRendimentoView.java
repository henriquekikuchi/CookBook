package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.Scanner;

public class NovoRendimentoView {
    private Scanner scanner = new Scanner(System.in);
    private int quantidade;
    private TipoRendimento tipoRendimento;


    private void askQuantidade() {
        System.out.println("Qual o rendimento da receita?");
        int quantidade = scanner.nextInt();
        if (quantidade < 0) {
            System.out.println("Quantidade invalida!");
            askQuantidade();
        } else {
            this.quantidade = quantidade;
        }
    }

    private void askTipoRendimento() {
        System.out.println("Qual a categoria da receita?");
        for (TipoRendimento cat : TipoRendimento.values()) {
            System.out.printf("%d - %s%n", cat.ordinal(), cat.name());
        }
        int tipoRendimento = scanner.nextInt();
        if (tipoRendimento < 0 || tipoRendimento >= TipoRendimento.values().length) {
            System.out.println("Categoria inválida!");
            askTipoRendimento();
        } else {
            this.tipoRendimento = TipoRendimento.values()[tipoRendimento];
        }
    }

    public Rendimento createNewRendimento(){
        askQuantidade();
        askTipoRendimento();
        Rendimento rendimento = new Rendimento(quantidade, tipoRendimento);
        return rendimento;
    }

}
