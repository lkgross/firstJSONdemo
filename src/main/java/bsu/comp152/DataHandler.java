package bsu.comp152;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class DataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public DataHandler(String webLocation){
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    public ArrayList<recipeDataType> getData(){
        /*
         * The HttpRequest object packages up everything
         * that we need to make a request of a website.
         */
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri
                (URI.create("http://www.recipepuppy.com/api/" +
                        "?i=mushrooms&q=casserole")).build();
        HttpResponse<String> response = null;

        // Let's ask the server for data.
        try{
            response = dataGrabber.send(dataRequest,
                    HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException e){
            System.out.println("Error connecting to network or site");
        }
        catch (InterruptedException e){
            System.out.println("Connection to site broken");
        }
        if (response == null){
            System.out.println("Something went wrong, ending program");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        var recipeData = jsonInterpreter.fromJson(usefulData,
                responseDataType.class);
        return recipeData.results;
    }

    class responseDataType {
        String title;
        float version;
        String href;
        ArrayList<recipeDataType> results;
    }

    class recipeDataType {
        String title;
        String href;
        String ingredients;
        String thumbnail;
        @Override
        public String toString(){
            return "Recipe Title: " + title;
        }
    }
}
