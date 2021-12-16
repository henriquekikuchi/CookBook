package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NovaReceitaView {
    private Scanner scanner;
    private Receita receita;
    private String nome;
    private Categoria categoria;
    private int tempoDePreparo;
    private Rendimento rendimento;
    private List<Ingrediente> ingredientes;
    private List<String> modoPreparo;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public Receita getReceita(){
        return receita;
    }

    public void createNewReceita(){
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = new ArrayList<>();
        ScreenUtil.clearScreen();
        askNome();
        ScreenUtil.clearScreen();
        askCategoria();
        ScreenUtil.clearScreen();
        askTempoPreparo();
        ScreenUtil.clearScreen();
        askRendimento();
        ScreenUtil.clearScreen();
        askIngredientes();
        ScreenUtil.clearScreen();
        askModoPreparo();
        ScreenUtil.clearScreen();
        this.receita = new Receita(nome, categoria);
        this.receita.setTempoPreparo(tempoDePreparo);
        this.receita.setRendimento(rendimento);
        this.receita.setIngredientes(ingredientes);
        this.receita.setModoPreparo(modoPreparo);
        System.out.println("Nova receita criada com sucesso");
    }

    public void askNome() {
        System.out.println("Qual o nome da receita?");
        nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }
        this.nome = nome;
    }

    public void askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s%n", cat.ordinal(), cat.name());
        }
        int categoria = scanner.nextInt();
        if (categoria < 0 || categoria >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        } else {
            this.categoria = Categoria.values()[categoria];
        }
    }

    public void askTempoPreparo() {
        System.out.println("Qual é o tempo de preparo em minutos?");
        int tempoDePreparo = scanner.nextInt();
        if (tempoDePreparo <= 0){
            System.out.println("Tempo de preparo invalido!");
            askTempoPreparo();
        }
    }

    public void askRendimento() {
        rendimento = new NovoRendimentoView().createNewRendimento();
    }

    public void askModoPreparo(){
        String etapa = "";
        scanner.nextLine();
        System.out.println("Modo de preparo em etapas:");
        do {
            etapa = scanner.nextLine();
            if (etapa.length() > 0) {
                this.modoPreparo.add(etapa);
            }
        } while (etapa.length() != 0);
    }

    public void askIngredientes() {
        String cadastrarNovoIngrediente = "y";
        do {
            Ingrediente ingrediente = askIngrediente();
            if(ingrediente != null) {
                this.ingredientes.add(ingrediente);
            }
            ScreenUtil.clearScreen();
            System.out.println("Se desejar cadastrar novos ingredientes pressione Y caso contrario N:");
            cadastrarNovoIngrediente = scanner.next();
        } while (cadastrarNovoIngrediente.equalsIgnoreCase("y"));

    }

    private Ingrediente askIngrediente() {
        System.out.println("Nome do ingrediente: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.println("Quantidade: ");
        double quantidade = Double.parseDouble(scanner.nextLine());
        System.out.println("Unidade de medida: ");
        for (TipoMedida tipo : TipoMedida.values()) {
            System.out.printf("%d - %s%n", tipo.ordinal(), tipo.name());
        }
        int tipoMedidaIdx = scanner.nextInt();
        if (tipoMedidaIdx < 0 || tipoMedidaIdx > TipoMedida.values().length){
            System.out.println("Unidade de medida invalido!");
            askIngrediente();
        } else {
            TipoMedida tipoMedida = TipoMedida.values()[tipoMedidaIdx];
            Ingrediente ingrediente = new Ingrediente(nome, quantidade, tipoMedida);
            return ingrediente;
        }
        return null;
    }
}
