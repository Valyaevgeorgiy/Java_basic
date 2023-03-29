package com.example.speedometerfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Timer;

public class Speedometer extends Application {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int CENTER_X = WIDTH / 2;
    private static final int CENTER_Y = HEIGHT / 2;
    private static final double MAX_SPEED = 180.0;
    private static final int RADIUS = Math.min(CENTER_X, CENTER_Y) - 20;

    private double currentSpeed = 0;
    private boolean isIncreasing = true;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        Label speedLabel = new Label("0");
        speedLabel.setFont(new Font("Comfortaa", 50));
        VBox vbox = new VBox(speedLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        root.setBottom(vbox);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isIncreasing) {
                    currentSpeed += 0.5;
                    if (currentSpeed > 270) {
                        isIncreasing = false;
                    }
                } else {
                    currentSpeed -= 1;
                    if (currentSpeed <= 0) {
                        isIncreasing = true;
                    }
                }
                double normSpeed = (currentSpeed / 270) * MAX_SPEED;
                speedLabel.setText(String.format("%.0f", normSpeed) + " км/ч");
                drawSpeedometer(gc);
            }
        };
        timer.start();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Спидометр (JavaFX) Валяев № 6");
        primaryStage.show();
    }

    private void drawSpeedometer(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        // Draw the speedometer face
        gc.setFill(Color.WHITE);
        gc.fillOval(CENTER_X - RADIUS, CENTER_Y - RADIUS, RADIUS * 2, RADIUS * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(CENTER_X - RADIUS, CENTER_Y - RADIUS, RADIUS * 2, RADIUS * 2);

        for (int i = 0; i <= 180; i++) {
            // Draw the speedometer markings
            gc.setFont(new Font(20));
            gc.setStroke(Color.GRAY);
            double angle = (i * 1.5) * Math.PI / 180 - 2.3;
            gc.setLineWidth(2);

            if (i < 140) {
                gc.strokeLine(CENTER_X + Math.sin(angle) * (RADIUS - 34),
                        CENTER_Y - Math.cos(angle) * (RADIUS - 34),
                        CENTER_X + Math.sin(angle) * (RADIUS - 41),
                        CENTER_Y - Math.cos(angle) * (RADIUS - 41));

                if (i % 20 == 0) {
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(3);
                    gc.strokeLine(CENTER_X + Math.sin(angle) * (RADIUS - 40),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 40),
                            CENTER_X + Math.sin(angle) * (RADIUS - 60),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 60));

                    Font font = new Font("Comfortaa", 23);
                    gc.setFont(font);

                    gc.setLineWidth(2);
                    if (i < 100) {
                        gc.strokeText(Integer.toString(i),
                                CENTER_X + Math.sin(angle) * (RADIUS - 70),
                                CENTER_Y - Math.cos(angle) * (RADIUS - 80)
                        );
                    } else {
                        gc.strokeText(Integer.toString(i),
                                CENTER_X + Math.sin(angle) * (RADIUS - 100),
                                CENTER_Y - Math.cos(angle) * (RADIUS - 80)
                        );
                    }

                } else if (i % 10 == 0) {
                    gc.setStroke(Color.BLACK);
                    gc.strokeLine(CENTER_X + Math.sin(angle) * (RADIUS - 38),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 38),
                            CENTER_X + Math.sin(angle) * (RADIUS - 53),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 53));
                }
            } else {
                gc.setStroke(Color.RED);
                gc.strokeLine(CENTER_X + Math.sin(angle) * (RADIUS - 34),
                        CENTER_Y - Math.cos(angle) * (RADIUS - 34),
                        CENTER_X + Math.sin(angle) * (RADIUS - 41),
                        CENTER_Y - Math.cos(angle) * (RADIUS - 41));

                if (i % 20 == 0) {
                    gc.setLineWidth(3);
                    gc.strokeLine(CENTER_X + Math.sin(angle) * (RADIUS - 40),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 40),
                            CENTER_X + Math.sin(angle) * (RADIUS - 60),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 60));

                    Font font = new Font("Comfortaa", 23);
                    gc.setFont(font);

                    gc.setLineWidth(2);
                    if (i == 140) {
                        gc.strokeText(Integer.toString(i),
                                CENTER_X + Math.sin(angle) * (RADIUS - 97),
                                CENTER_Y - Math.cos(angle) * (RADIUS - 97));
                    } else {
                        gc.strokeText(Integer.toString(i),
                                CENTER_X + Math.sin(angle) * (RADIUS - 90),
                                CENTER_Y - Math.cos(angle) * (RADIUS - 90));
                    }

                } else if (i % 10 == 0) {
                    gc.strokeLine(CENTER_X + Math.sin(angle) * (RADIUS - 38),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 38),
                            CENTER_X + Math.sin(angle) * (RADIUS - 53),
                            CENTER_Y - Math.cos(angle) * (RADIUS - 53));
                }
            }
        }

        // Draw the speedometer needle
        double speedAngle = 450 + (currentSpeed) * Math.PI / 180;
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);

        gc.strokePolygon(
                new double[]{CENTER_X + Math.sin(speedAngle),
                        CENTER_X + Math.sin(speedAngle) * (RADIUS - 115),
                        CENTER_X + Math.sin(speedAngle),
                        CENTER_X + Math.sin(speedAngle) * (RADIUS - 20)},
                new double[]{CENTER_Y - Math.cos(speedAngle),
                        CENTER_Y - Math.cos(speedAngle) * (RADIUS - 115),
                        CENTER_Y - Math.cos(speedAngle),
                        CENTER_Y - Math.cos(speedAngle) * (RADIUS - 20)}, 3);

        gc.setFill(Color.RED);
        int centerCircleRadius = RADIUS / 14;
        gc.fillOval(CENTER_X - centerCircleRadius,
                CENTER_Y - centerCircleRadius, centerCircleRadius * 2, centerCircleRadius * 2);

        gc.strokeArc(CENTER_X - RADIUS + 5,
                CENTER_Y - RADIUS + 5,
                RADIUS * 2 - 10,
                RADIUS * 2 - 10,
                222,
                -currentSpeed / 270 * MAX_SPEED * 2,
                ArcType.OPEN);

        gc.setStroke(Color.BLACK);
        gc.strokeArc(CENTER_X - RADIUS + 10,
                CENTER_Y - RADIUS + 10,
                RADIUS * 2 - 20,
                RADIUS * 2 - 20,
                222,
                -currentSpeed / 270 * MAX_SPEED * 2,
                ArcType.OPEN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


