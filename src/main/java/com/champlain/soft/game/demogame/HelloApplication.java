package com.champlain.soft.game.demogame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }


    // 🔹 Grid constants
    private static final int SCENE_WIDTH = 100;
    private static final int SCENE_HEIGHT = 100;
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_WIDTH = 800;
    private static final int CELL_HEIGHT = 800;

    enum CellType {
        GRASS, PLAYER, PRINCESS, BOMB, WALL
    }
    private Image grassImage;
    private Image playerImage;
    private Image princessImage;
    private Image bombImage;
    private Image wallImage;

    // 🔹 Use "matrix" instead of "map"
    private CellType[][] matrix = new CellType[ROWS][COLS];
    private final Random random = new Random();


    @Override
    public void start(Stage stage) {

        initMatrix();
        GridPane grid = new GridPane();
        loadImages();
        drawBoard(grid);

        BorderPane root = new BorderPane();
        root.setCenter(grid);

        Scene scene = new Scene(root, 100,100);

        stage.setTitle("Rescue the Princess");
        stage.setScene(scene);
        stage.show();
    }

    private void loadImages() {
        grassImage = new Image(getClass().getResource("images/grass.png").toExternalForm());
        playerImage = new Image(getClass().getResource("images/player.png").toExternalForm());
        princessImage = new Image(getClass().getResource("images/princess.png").toExternalForm());
        bombImage = new Image(getClass().getResource("images/bomb.png").toExternalForm());
        wallImage = new Image(getClass().getResource("images/wall.png").toExternalForm());
    }

    private void initMatrix() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                matrix[r][c] = CellType.GRASS;
            }
        }

        // Sample objects
        matrix[0][0] = CellType.PLAYER;
        matrix[9][9] = CellType.PRINCESS;
        matrix[4][5] = CellType.BOMB;
        matrix[1][1] = CellType.WALL;
        matrix[1][2] = CellType.WALL;
    }

    private void drawBoard(GridPane grid) {
        grid.getChildren().clear();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                StackPane cell = new StackPane();
                cell.setPrefSize(CELL_WIDTH, CELL_HEIGHT);
                cell.setStyle("-fx-border-color: black; -fx-background-color: beige;");

                //Label label = new Label();

                Image entityImage = null;
                if(matrix[row][col] == CellType.PLAYER ) entityImage = playerImage;
                else if(matrix[row][col] == CellType.PRINCESS ) {
                    entityImage = princessImage;
                }else if(matrix[row][col] == CellType.BOMB){
                    entityImage = bombImage;
                }else if(matrix[row][col] == CellType.WALL){
                    entityImage = wallImage;
                    cell.getChildren().clear();
                }
                if (entityImage != null) {
                    cell.getChildren().add(createImageView(entityImage));
                }

                grid.add(cell, col, row);
            }

        }
    }

    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(SCENE_WIDTH);
        imageView.setFitHeight(SCENE_HEIGHT);
        imageView.setPreserveRatio(false);
        return imageView;
    }
}
