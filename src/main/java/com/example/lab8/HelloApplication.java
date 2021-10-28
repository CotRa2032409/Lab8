package com.example.lab8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Flow;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Lab8");

        ImageView mario0 = new ImageView("file:mario0.jpg");
        ImageView mario1 = new ImageView("file:mario1.jpg");
        ImageView mario2 = new ImageView("file:mario2.jpg");
        ImageView mario3 = new ImageView("file:mario3.jpg");
        ImageView mario4 = new ImageView("file:mario4.jpg");
        ImageView mario5 = new ImageView("file:mario5.jpg");
        ImageView mario6 = new ImageView("file:mario6.jpg");
        ImageView mario7 = new ImageView("file:mario7.jpg");
        ImageView mario8 = new ImageView("file:mario8.jpg");

        Image imageMario0 = new Image("file:mario0.jpg");
        Image imageMario1 = new Image("file:mario1.jpg");
        Image imageMario2 = new Image("file:mario2.jpg");
        Image imageMario3 = new Image("file:mario3.jpg");
        Image imageMario4 = new Image("file:mario4.jpg");
        Image imageMario5 = new Image("file:mario5.jpg");
        Image imageMario6 = new Image("file:mario6.jpg");
        Image imageMario7 = new Image("file:mario7.jpg");
        Image imageMario8 = new Image("file:mario8.jpg");


        ArrayList<Image> listeImageBase = new ArrayList<>();
        ArrayList<Image> imageMixed = new ArrayList<>();
        ArrayList<ImageView> listeImageView = new ArrayList<>();
        listeImageBase.add(imageMario0);
        listeImageBase.add(imageMario1);
        listeImageBase.add(imageMario2);
        listeImageBase.add(imageMario3);
        listeImageBase.add(imageMario4);
        listeImageBase.add(imageMario5);
        listeImageBase.add(imageMario6);
        listeImageBase.add(imageMario7);
        listeImageBase.add(imageMario8);
        imageMixed.add(imageMario0);
        imageMixed.add(imageMario1);
        imageMixed.add(imageMario2);
        imageMixed.add(imageMario3);
        imageMixed.add(imageMario4);
        imageMixed.add(imageMario5);
        imageMixed.add(imageMario6);
        imageMixed.add(imageMario7);
        imageMixed.add(imageMario8);

        Collections.shuffle(imageMixed);
        for (int i = 0; i < imageMixed.size(); i++) {
            listeImageView.add(i, new ImageView(imageMixed.get(i)));

        }

        GridPane baseImages = new GridPane();
        baseImages.addRow(0, listeImageView.get(0), listeImageView.get(1), listeImageView.get(2));
        baseImages.addRow(1, listeImageView.get(3), listeImageView.get(4), listeImageView.get(5));
        baseImages.addRow(2, listeImageView.get(6), listeImageView.get(7), listeImageView.get(8));


        Scene scene = new Scene(baseImages);

        scene.setOnKeyPressed((ae) -> {
            if (ae.isControlDown() && ae.getCode() == KeyCode.M) {
                Collections.shuffle(imageMixed);
                System.out.println("Image mélangées");
                for (int i = 0; i < imageMixed.size(); i++) {
                    listeImageView.set(i, new ImageView(imageMixed.get(i)));
                    baseImages.getChildren().set(i, listeImageView.get(i));
                }
            }
        });


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}