package com.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    public static String conectarAPI(String url_link) {
        try {
            URL url = new URL(url_link);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("GET");
            int response_code = conexion.getResponseCode();
            if (response_code == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null){

                    response.append(line);


                }

                reader.close();

                String data = response.toString();
                conexion.disconnect();
                System.out.println(data);
                return data;
                
            }else {
                throw new RuntimeException("Error al conectarnos a la API: Response Code:" + response_code);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        /* Introduce un link a una API dentro de la funcion*/
        conectarAPI("https://pokeapi.co/api/v2/pokemon/ditto");

    }
}