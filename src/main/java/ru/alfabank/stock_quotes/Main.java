package ru.alfabank.stock_quotes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.alfabank.stock_quotes.controller.FXController;

import java.io.InputStream;

public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(FXController.class);
        Scene scene = new Scene(root);

        InputStream inputStreamImages = getClass().getClassLoader().getResourceAsStream("img/alfa-logo.png");
        Image alfaLogo = new Image(inputStreamImages);
        primaryStage.getIcons().add(alfaLogo);

        primaryStage.setTitle("Alfabank: Тестовое задание - Курсы валют");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(StockQuotesApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
