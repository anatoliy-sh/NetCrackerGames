package gamepub.console;

import gamepub.db.dao.implementation.GameDaoImplementation;
import gamepub.db.dao.implementation.NewsDaoImplementation;
import gamepub.db.dao.implementation.PlatformDaoImplementation;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import gamepub.db.entity.Platform;
import gamepub.db.service.GameService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 10.12.15.
 */

public class Main {
    private static final String USER_AGENT="Mozilla/5.0";

    public static void main(String[] args) throws Exception{
        Main loader = new Main();
        String gameUrl = "http://api.steampowered.com/ISteamApps/GetAppList/v2"; //all games
        String newsUrl = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid="; //news by game
        JSONObject jsonObject = new JSONObject((new Main()).sendGet(gameUrl));
        JSONArray jsonArray = jsonObject.getJSONObject("applist").getJSONArray("apps");
        Game game;
        GameDaoImplementation gameDaoImplementation = new GameDaoImplementation();
        for(int i = 0; i<jsonArray.length(); i++){
          game = new Game();
          game.setName(jsonArray.getJSONObject(i).getString("name"));
          game.setSteamId(jsonArray.getJSONObject(i).getInt("appid"));
          game.setLinkToSteam("http://store.steampowered.com/app/" + game.getSteamId());
          game.setName(jsonArray.getJSONObject(i).getString("name"));
          gameDaoImplementation.create(game);
        }
        GameDaoImplementation gameService = new GameDaoImplementation();
        List<Game> games = gameService.findAll();
        NewsDaoImplementation newsDaoImplementation = new NewsDaoImplementation();
        News news;
        for(int i=0; i<games.size(); i++){
            game = games.get(i);
            Thread.sleep(1000);
            String json = loader.sendGet(newsUrl+game.getSteamId()+"\"");
            if (json != null) {
                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONObject("appnews").getJSONArray("newsitems");
                for (int j = 0; j < jsonArray.length(); j++) {
                    news = new News();
                    news.setGame(game);
                    news.setName(jsonArray.getJSONObject(j).getString("title"));
                    news.setLink(jsonArray.getJSONObject(j).getString("url"));
                    news.setDate(new Date(jsonArray.getJSONObject(j).getLong("date")*1000));
                    newsDaoImplementation.create(news);
                }
            }
        }
        System.out.println("Size = " + newsDaoImplementation.findAll().size());
        System.exit(0);
    }

    private String sendGet(String stringUrl) throws Exception{
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        while (responseCode == 503) {
            Thread.sleep(30000);
            responseCode = connection.getResponseCode();
        }
        if (responseCode == 200){
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            //inputLine = input.toString();
            StringBuffer response = new StringBuffer();
            while ((inputLine = input.readLine()) !=null){
                response.append(inputLine);
            }
            input.close();
            return response.toString();
        }
        return null;
    }
}
