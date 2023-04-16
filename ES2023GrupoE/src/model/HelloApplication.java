package model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class HelloApplication extends Application {

    private static final int ROW_HEIGHT = 30; // altura de cada linha
    private static final int NUM_ROWS = 21; // número de linhas (cada linha representa um intervalo de 30 min)
    private static final int NUM_COLS = 5; // número de colunas (cada coluna representa um dia da semana)
    private static final String[] DIAS_DA_SEMANA = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};

    @Override
    public void start(Stage primaryStage) {

        // criar uma grade com o número de linhas e colunas apropriados
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < NUM_ROWS; i++) {
            // adicionar um rótulo para cada intervalo de 30 min
            String hora = String.format("%02d:%02d", 8 + i / 2, (i % 2) * 30);
            Label horaLabel = new Label(hora);
            grid.add(horaLabel, 0, 1+i);

            for (int j = 0; j < NUM_COLS; j++) {
                // adicionar um rótulo para cada dia da semana
                if (i == 0) {
                    Label diaLabel = new Label(DIAS_DA_SEMANA[j]);
                    grid.add(diaLabel, j + 1, i);
                } else {
                    // adicionar um rótulo com o nome da disciplina correspondente ao "block"
                    Label disciplinaLabel = new Label();
                    disciplinaLabel.setPrefHeight(ROW_HEIGHT);
                    disciplinaLabel.setPadding(new Insets(5, 50, 5, 5));
                    disciplinaLabel.setStyle("-fx-border-color: black");
                    grid.add(disciplinaLabel, j + 1, i);
                }
            }
        }

        // preencher as células com o nome da disciplina correspondente ao "block"
        List<Block> blocks = getBlocks();
        for (Block block : blocks) {
            int aux = block.getHour_begin().getHour()*60+block.getHour_begin().getMinute();
            int row = (aux - 450) / 30; // calcular o índice da linha correspondente
            int col = block.getDayOfWeek().ordinal() + 1; // calcular o índice da coluna correspondente
            Label disciplinaLabel = (Label) grid.getChildren().get(row * (NUM_COLS + 1) + col); // encontrar o rótulo correspondente à célula
            disciplinaLabel.setText("Disciplina: " + block.getCurricular_unit() + "\n" +
                    "Alunos Inscritos: " + block.getNumberOfSubscribers() + "\n" +
                    "Sala: " + block.getRoom() + "\n" +
                    "Limite da Sala: " + block.getSizeRoom() + "\n" +
                    "Time: " + block.getTeam() + "\n" +
                    "Turno: " + block.getShift());
            System.out.println(block.getNumberOfSubscribers());
            System.out.println(block.getRoom());
            System.out.println(block.getSizeRoom());
            System.out.println(block.getTeam());

        }

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horário");
        primaryStage.show();
    }

    private List<Block> getBlocks() {
        // exemplo de lista de "blocks"
        List<Block> blocks = List.of(
                new Block("Matemática", "Álgebra Linear", "Manhã", "A", "20", "MONDAY", "08:00", "10:00", "2023-04-17", "101", "30"),
                new Block("Física", "Física Moderna", "Manhã", "B", "25", "TUESDAY", "10:00", "12:00", "2023-04-18", "201", "35"),
                new Block("Química", "Química Orgânica", "Manhã", "C", "30", "WEDNESDAY", "08:00", "10:00", "2023-04-19", "301", "40"),
                new Block("Biologia", "Genética", "Manhã", "D", "35", "THURSDAY", "08:00", "10:00", "2023-04-20", "401", "45"),
                new Block("História", "História do Brasil", "Manhã", "E", "40", "FRIDAY", "10:00", "12:00", "2023-04-21", "501", "50"),
                new Block("Filosofia", "Filosofia da Ciência", "Tarde", "A", "20", "MONDAY", "14:00", "16:00", "2023-04-17", "102", "30"),
                new Block("Geografia", "Geografia Humana", "Tarde", "B", "25", "TUESDAY", "16:00", "18:00", "2023-04-18", "202", "35"),
                new Block("Artes", "Pintura", "Tarde", "C", "30", "WEDNESDAY", "14:00", "16:00", "2023-04-19", "302", "40"),
                new Block("Educação Física", "Basquete", "Tarde", "D", "35", "THURSDAY", "16:00", "18:00", "2023-04-20", "402", "45"),
                new Block("Música", "Teoria Musical", "Tarde", "E", "40", "FRIDAY", "14:00", "16:00", "2023-04-21", "502", "50")
                );
        return blocks;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
