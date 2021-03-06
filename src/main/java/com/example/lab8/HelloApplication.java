package com.example.lab8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Lab8");
        stage.setHeight(603);
        stage.setWidth(603);

        ImageView[] marioView = new ImageView[9];
        Image[] imageMario = new Image[9];
        for (int i = 0; i < 9; i++) {
            marioView[i] = new ImageView("file:mario" + i + ".jpg");
            imageMario[i] = new Image("file:mario" + i + ".jpg");
        }
        ArrayList<Image> listeImageBase = new ArrayList<>();
        ArrayList<Image> imageMixed = new ArrayList<>();
        ArrayList<ImageView> listeImageView = new ArrayList<>();

        Collections.addAll(listeImageBase, imageMario);
        Collections.addAll(imageMixed, imageMario);
        Collections.addAll(listeImageView, marioView);

        Collections.shuffle(imageMixed);
        for (int i = 0; i < imageMixed.size(); i++) {
            listeImageView.get(i).setImage(imageMixed.get(i));
        }

        GridPane baseImages = new GridPane();
        baseImages.addRow(0, listeImageView.get(0), listeImageView.get(1), listeImageView.get(2));
        baseImages.addRow(1, listeImageView.get(3), listeImageView.get(4), listeImageView.get(5));
        baseImages.addRow(2, listeImageView.get(6), listeImageView.get(7), listeImageView.get(8));

        Scene scene = new Scene(baseImages);

        Button restart = new Button("Recommencer");
        Button cancel = new Button("Annuler");


        scene.setOnKeyPressed((ae) -> {
            if (ae.isControlDown() && ae.getCode() == KeyCode.M) {
                Collections.shuffle(imageMixed);
                baseImages.getChildren().clear();
                System.out.println("Image m??lang??es");
                for (int i = 0; i < imageMixed.size(); i++) {
                    listeImageView.get(i).setImage(imageMixed.get(i));
                }
                baseImages.addRow(0, listeImageView.get(0), listeImageView.get(1), listeImageView.get(2));
                baseImages.addRow(1, listeImageView.get(3), listeImageView.get(4), listeImageView.get(5));
                baseImages.addRow(2, listeImageView.get(6), listeImageView.get(7), listeImageView.get(8));
            }
        });

        for (ImageView temp : marioView) {
            temp.setOnDragDetected((ae) -> {
                System.out.println("Drag and drop d??tect??");
                Dragboard dragboard = temp.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(temp.getImage());
                dragboard.setContent(content);
            });
            temp.setOnDragOver((ae) -> {
                ae.acceptTransferModes(TransferMode.MOVE);
            });
            temp.setOnDragDropped(ae -> {
                ImageView source = (ImageView) ae.getGestureSource();
                Image imageSource = source.getImage();
                source.setImage(temp.getImage());
                temp.setImage(imageSource);
                System.out.println("Drag and drop confirm??");
                ae.setDropCompleted(true);
                int test = 0;
                for (int i = 0; i < listeImageView.size(); i++) {
                    if (listeImageView.get(i).getImage() == listeImageBase.get(i)) {
                        test++;
                    }
                }
                if (test == 9) {
                    Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
                    alerte.setTitle("Veuillez lire");
                    alerte.setHeaderText("F??licitations!!");
                    alerte.setContentText("Veux-tu recommencer le casse-t??te?");
                    ButtonType resultat = alerte.showAndWait().get();
                    if (resultat == ButtonType.OK) {
                        Collections.shuffle(imageMixed);
                        baseImages.getChildren().clear();
                        for (int i = 0; i < imageMixed.size(); i++) {
                            listeImageView.get(i).setImage(imageMixed.get(i));
                        }
                        baseImages.addRow(0, listeImageView.get(0), listeImageView.get(1), listeImageView.get(2));
                        baseImages.addRow(1, listeImageView.get(3), listeImageView.get(4), listeImageView.get(5));
                        baseImages.addRow(2, listeImageView.get(6), listeImageView.get(7), listeImageView.get(8));
                    }
                    else
                        stage.close();
                }
            });
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}