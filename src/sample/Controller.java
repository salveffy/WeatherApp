package sample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_felt;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text pressure;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();

            try {
                getUserCity = URLEncoder.encode(getUserCity, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (!getUserCity.equals("")) {
                String getApi ="";
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("C:/Users/salveffy/IdeaProjects/WeatherApp/src/sample/OpenWeatherApi.txt"));
                    getApi = reader.readLine();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid=" + getApi);
                System.out.println(output);

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("t " + obj.getJSONObject("main").getDouble("temp") + "°с");
                    temp_felt.setText("felt " + obj.getJSONObject("main").getDouble("feels_like") + "°с");
                    temp_max.setText("Max " + obj.getJSONObject("main").getDouble("temp_max") + "°с");
                    temp_min.setText("Min " + obj.getJSONObject("main").getDouble("temp_min") + "°с");
                    pressure.setText("p. " + obj.getJSONObject("main").getDouble("pressure") + " mbar");

                }
            }
        });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Такой город не найден");
        }
        return content.toString();
    }
}

