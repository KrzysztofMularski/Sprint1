package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        final ComboBox<String> restBox = new ComboBox<String>();
        restBox.getItems().addAll(
                "POST",
                "GET"
        );
        restBox.setValue("POST");

        TextField url = new TextField();

        Button send = new Button("Send");

        TextArea body = new TextArea();

        TextArea result = new TextArea();

        Label label1 = new Label();
        label1.setText("Parameters:");

        TextField params = new TextField();

        url.setPromptText("Enter request URL");

        result.setEditable(false);

        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                result.setText("EHLLOO");

                String urlAddress = url.getText().toString();
                String charset = "UTF-8";
                String params1 = params.getText().toString();

                String query = null;
                try {
                    query = String.format("transforms=%s", URLEncoder.encode(params1, charset));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                URLConnection connection = null;
                try {
                    connection = new URL(urlAddress + "?" + query).openConnection();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                connection.setRequestProperty("Accept-Charset", charset);
                if ( connection instanceof HttpURLConnection)
                {
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    try {
                        result.setText(httpConnection.getResponseMessage().toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5,5,5,5));
        grid.add(restBox,0,0);
        grid.add(url,1,0, 2, 1);
        grid.add(send,3,0);
        grid.add(label1, 0, 1);
        grid.add(params, 1, 1);
        grid.add(body, 0, 2, 2, 1);
        grid.add(result, 0, 3, 2, 1);

        Scene scene = new Scene(new Group(), 700, 520);

        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @Override
    public void stop() throws Exception {
        super.stop();
    }


}
