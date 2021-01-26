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

import java.io.*;
import java.net.*;


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
                "POST"
        );
        restBox.setValue("POST");

        TextField urlText = new TextField();
        urlText.setText("localhost:8080");

        Button send = new Button("Send");

        TextArea body = new TextArea();
        body.setText("Dzień dobry");

        TextArea result = new TextArea();

        Label label1 = new Label();
        label1.setText("Parameters:");

        TextField params = new TextField();
        params.setText("upper,reverse");

        urlText.setPromptText("Enter request URL");

        result.setEditable(false);


        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                String urlAddress = url.getText().toString();
                String charset = "UTF-8";
                String params1 = params.getText().toString();

            try {
                URL url = new URL("http://" + urlText.getText()+"/?transforms=" + params.getText());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                String input = body.getText();

                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String output;
                System.out.println("Output from Server .... \n");
                String resultText = "";
                while ((output = br.readLine()) != null) {
                    resultText = resultText + output;
                    result.setText(resultText);
                }

                conn.disconnect();
                } catch (MalformedURLException ex) {

                ex.printStackTrace();

                } catch (IOException ex) {

                ex.printStackTrace();
                }

            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5,5,5,5));
        grid.add(restBox,0,0);
        grid.add(urlText,1,0, 2, 1);
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
