package gamepub.console;

import gamepub.db.dao.implementation.*;
import gamepub.db.entity.*;
import gamepub.db.service.GameService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by roman on 10.12.15.
 */

public class Main {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GAME_URL = "http://api.steampowered.com/ISteamApps/GetAppList/v2"; //all games
    private static final String NEWS_URL = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid="; //news by game

    public static void load(String filename, String platformName) throws Exception{
        GameDaoImplementation gameDaoImplementation = new GameDaoImplementation();
        PlatformDaoImplementation platformDaoImplementation = new PlatformDaoImplementation();
        GamePlatformDaoImplementation gamePlatformDaoImplementation = new GamePlatformDaoImplementation();
        GameScreenshotDaoImplementation gameScreenshotDaoImplementation = new GameScreenshotDaoImplementation();
        GenreDaoImplementation genreDaoImplementation = new GenreDaoImplementation();
        GameGenreDaoImplementation gameGenreDaoImplementation = new GameGenreDaoImplementation();
        NewsDaoImplementation newsDaoImplementation = new NewsDaoImplementation();

        Main main = new Main();
        Path file = Paths.get(main.getClass().getClassLoader().getResource(filename).getPath());
        InputStream is = Files.newInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer html = new  StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            html.append(line);
        }
        Document document = Jsoup.parse(html.toString());
        Elements elements = document.select("li");
        for (Element element:elements){
            Game game;
            String name = element.select("div > div > div > h2 > a").text();
            name = name.substring(0, name.length() / 2);
            List<Game> games = gameDaoImplementation.getGamesByName(name.trim());
            if (games ==null || games.size() == 0){
                game = new Game();
                game.setName(name);
                game.setMetacritic(0);
                game.setReleaseDate(new Date(element.select("div > div:eq(1) > div > div > h3").text()));
                game.setPoster(element.select("div > div:eq(1) > div > a > img").attr("src").replaceAll("Small", "Large"));
                game.setLinkToSonyPlaystationStore(element.select("div > div > div > h2 > a").attr("href"));
                String tmp = main.sendGet(game.getLinkToSonyPlaystationStore());
                Document document1 = Jsoup.parse(tmp);
                game.setDescription(document1.select(".teaser").text());
                if (game.getDescription() != null && game.getDescription().trim().length()>0) {
                    game = gameDaoImplementation.create(game);
                    String genreString="";
                    try {
                        genreString = document1.select("ul.release-info:nth-child(5) > li:nth-child(2)").text();
                        if(!genreString.contains("Genre:"))
                            genreString = document1.select("ul.release-info:nth-child(4) > li:nth-child(2)").text();
                        if(!genreString.contains("Genre:"))
                            genreString = document1.select("ul.release-info:nth-child(6) > li:nth-child(2)").text();
                        genreString = genreString.substring(6);
                    }catch (Exception e){
                        System.out.println(game);
                    }

                    String[] genres = genreString.split("/");
                    for(String genreText:genres){
                        Genre genre = genreDaoImplementation.getGenreByName(genreText.trim());
                        if (genre==null){
                            genre = new Genre();
                            genre.setName(genreText.trim());
                            genre = genreDaoImplementation.create(genre);
                        }
                        GameGenre gameGenre = new GameGenre();
                        gameGenre.setGenre(genre);
                        gameGenre.setGame(game);
                        gameGenreDaoImplementation.create(gameGenre);
                    }
                }else game = null;
            }else game = games.get(0);
            if (game != null) {
                GamePlatform gamePlatform = new GamePlatform();
                gamePlatform.setGame(game);
                gamePlatform.setPlatform(platformDaoImplementation.getPlatformByName(platformName));
                gamePlatformDaoImplementation.create(gamePlatform);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        PlatformDaoImplementation platformDaoImplementation = new PlatformDaoImplementation();

        Platform platform;
        if (platformDaoImplementation.getPlatformByName("PS4") == null) {
            platform = new Platform();
            platform.setName("PS4");
            platformDaoImplementation.create(platform);
        }
        if (platformDaoImplementation.getPlatformByName("PS3") == null) {
            platform = new Platform();
            platform.setName("PS3");
            platformDaoImplementation.create(platform);
        }
        if (platformDaoImplementation.getPlatformByName("PS Vita") == null) {
            platform = new Platform();
            platform.setName("PS Vita");
            platformDaoImplementation.create(platform);
        }

        load("ps3.html","PS3");
        load("ps4.html","PS4");
        load("vita.html","PS Vita");

        System.exit(0);
/*
        //WARNING!!!! RUN IT ONLY ONCE!!!

        Main loader = new Main();

        JSONObject jsonObject = new JSONObject((new Main()).sendGet(GAME_URL));
        JSONArray jsonArray = jsonObject.getJSONObject("applist").getJSONArray("apps");
        Game game;
        News news;


        //PLATFORM INIT
        Platform platform;
        if (platformDaoImplementation.getPlatformByName("Windows") == null) {
            platform = new Platform();
            platform.setName("Windows");
            platformDaoImplementation.create(platform);
        }
        if (platformDaoImplementation.getPlatformByName("MAC-OS") == null) {
            platform = new Platform();
            platform.setName("MAC-OS");
            platformDaoImplementation.create(platform);
        }
        if (platformDaoImplementation.getPlatformByName("Linux") == null) {
            platform = new Platform();
            platform.setName("Linux");
            platformDaoImplementation.create(platform);
        }


        for (int i = 0; i < jsonArray.length(); i++) {

            if (gameDaoImplementation.getGamesByName(jsonArray.getJSONObject(i).getString("name")).size() == 0) {
                game = new Game();
                game.setName(jsonArray.getJSONObject(i).getString("name"));
                game.setSteamId(jsonArray.getJSONObject(i).getInt("appid"));
                game.setLinkToSteam("http://store.steampowered.com/app/" + game.getSteamId());
                game.setName(jsonArray.getJSONObject(i).getString("name"));
                JSONObject gameJSON;
                try {
                    gameJSON = new JSONObject((new Main()).sendGet("http://store.steampowered.com/api/appdetails?appids=" + game.getSteamId()));
                } catch (Exception e) {
                    System.out.println(i);
                    e.printStackTrace();
                    // Thread.sleep(1000);
                    gameJSON = new JSONObject((new Main()).sendGet("http://store.steampowered.com/api/appdetails?appids=" + game.getSteamId()));
                }

                Boolean inStore = gameJSON.getJSONObject(String.valueOf(game.getSteamId())).getBoolean("success");
                if (inStore) {
                    gameJSON = gameJSON.getJSONObject(String.valueOf(game.getSteamId())).getJSONObject("data");
                    String type = gameJSON.getString("type");
                    if (type.equals("game")) {
                        game.setDescription(gameJSON.getString("detailed_description"));
                        game.setPoster(gameJSON.getString("header_image"));
                        game.setReleaseDate(new Date(gameJSON.getJSONObject("release_date").getString("date")));
                        try {
                            game.setMetacritic(gameJSON.getJSONObject("metacritic").getInt("score"));
                        }catch (JSONException e){
                            game.setMetacritic(0);
                        }
                        game = gameDaoImplementation.create(game);
                        //GENRES
                        try {
                            JSONArray genres = gameJSON.getJSONArray("genres");
                            Genre genre;
                            GameGenre gameGenre;
                            for (int j = 0; j < genres.length(); j++) {
                                String textGenre = genres.getJSONObject(j).getString("description");
                                genre = genreDaoImplementation.getGenreByName(textGenre);
                                if (genre == null) {
                                    genre = new Genre();
                                    genre.setName(textGenre);
                                    genre = genreDaoImplementation.create(genre);
                                }
                                gameGenre = new GameGenre();
                                gameGenre.setGame(game);
                                gameGenre.setGenre(genre);
                                gameGenreDaoImplementation.create(gameGenre);
                            }
                        } catch (JSONException e) {

                        }
                        //SCREENSHOTS
                        try {
                            JSONArray screens = gameJSON.getJSONArray("screenshots");
                            GameScreenshot gameScreenshot;
                            for (int j = 0; j < screens.length(); j++) {
                                gameScreenshot = new GameScreenshot();
                                gameScreenshot.setGame(game);
                                gameScreenshot.setLink(Jsoup.parse(screens.getJSONObject(j).getString("path_full")).text());
                                gameScreenshotDaoImplementation.create(gameScreenshot);
                            }
                        } catch (JSONException e) {

                        }
                        //SYS REQ
                        GamePlatform gamePlatform;
                        String winReq;
                        String macReq;
                        String linuxReq;
                        try {

                            winReq = Jsoup.parse(gameJSON.getJSONObject("pc_requirements").getString("minimum")).text();
                        } catch (Exception e) {
                            winReq = null;
                        }
                        try {
                            macReq = Jsoup.parse(gameJSON.getJSONObject("mac_requirements").getString("minimum")).text();
                        } catch (Exception e) {
                            macReq = null;
                        }
                        try {
                            linuxReq = Jsoup.parse(gameJSON.getJSONObject("linux_requirements").getString("minimum")).text();
                        } catch (Exception e) {
                            linuxReq = null;
                        }
                        if (winReq != null) {
                            gamePlatform = new GamePlatform();
                            gamePlatform.setGame(game);
                            gamePlatform.setPlatform(platformDaoImplementation.getPlatformByName("Windows"));
                            gamePlatform.setSystemRequirements(winReq);
                            gamePlatformDaoImplementation.create(gamePlatform);
                        }

                        if (macReq != null) {
                            gamePlatform = new GamePlatform();
                            gamePlatform.setGame(game);
                            gamePlatform.setPlatform(platformDaoImplementation.getPlatformByName("MAC-OS"));
                            gamePlatform.setSystemRequirements(macReq);
                            gamePlatformDaoImplementation.create(gamePlatform);
                        }

                        if (linuxReq != null) {
                            gamePlatform = new GamePlatform();
                            gamePlatform.setGame(game);
                            gamePlatform.setPlatform(platformDaoImplementation.getPlatformByName("Linux"));
                            gamePlatform.setSystemRequirements(linuxReq);
                            gamePlatformDaoImplementation.create(gamePlatform);
                        }
                        //NEWS
                        JSONArray newsJson;
                        String json = loader.sendGet(NEWS_URL + game.getSteamId() + "\"");
                        List<News> newses = newsDaoImplementation.getNewsByGameId(game.getId());
                        if (json != null) {
                            jsonObject = new JSONObject(json);
                            newsJson = jsonObject.getJSONObject("appnews").getJSONArray("newsitems");
                            if (newses == null || newses.size() == 0) {
                                for (int j = 0; j < newsJson.length(); j++) {
                                    news = new News();
                                    news.setGame(game);
                                    news.setName(newsJson.getJSONObject(j).getString("title"));
                                    news.setLink(newsJson.getJSONObject(j).getString("url"));
                                    news.setDate(new Date(newsJson.getJSONObject(j).getLong("date") * 1000));
                                    newsDaoImplementation.create(news);
                                }
                            } else {
                                int j = 0;
                                while (!newses.get(0).getDate().equals(new Date(newsJson.getJSONObject(j).getLong("date") * 1000))) {
                                    news = new News();
                                    news.setGame(game);
                                    news.setName(newsJson.getJSONObject(j).getString("title"));
                                    news.setLink(newsJson.getJSONObject(j).getString("url"));
                                    news.setDate(new Date(newsJson.getJSONObject(j).getLong("date") * 1000));
                                    newsDaoImplementation.create(news);
                                }
                            }
                        }
                    }
                }
            }
        Thread.sleep(1000);
        }
        loader.checkNewses();
        System.exit(0);
  */  }

    private void checkNewses(){
        GameDaoImplementation gameDaoImplementation = new GameDaoImplementation();
        NewsDaoImplementation newsDaoImplementation = new NewsDaoImplementation();
        News news;
        JSONObject jsonObject;
        JSONArray jsonArray;
        while (true){
            List<Game> games = gameDaoImplementation.findAll();
            for(Game game:games){
                try {
                    String json = sendGet(NEWS_URL + game.getSteamId() + "\"");
                    List<News> newses = newsDaoImplementation.getNewsByGameId(game.getId());
                    if (json != null) {
                        jsonObject = new JSONObject(json);
                        jsonArray = jsonObject.getJSONObject("appnews").getJSONArray("newsitems");
                        if (newses == null || newses.size() == 0) {
                            for (int j = 0; j < jsonArray.length(); j++) {
                                news = new News();
                                news.setGame(game);
                                news.setName(jsonArray.getJSONObject(j).getString("title"));
                                news.setLink(jsonArray.getJSONObject(j).getString("url"));
                                news.setDate(new Date(jsonArray.getJSONObject(j).getLong("date") * 1000));
                                newsDaoImplementation.create(news);
                            }
                        } else {
                            int j = 0;
                            while (!newses.get(0).getDate().equals(new Date(jsonArray.getJSONObject(j).getLong("date") * 1000))) {
                                news = new News();
                                news.setGame(game);
                                news.setName(jsonArray.getJSONObject(j).getString("title"));
                                news.setLink(jsonArray.getJSONObject(j).getString("url"));
                                news.setDate(new Date(jsonArray.getJSONObject(j).getLong("date") * 1000));
                                newsDaoImplementation.create(news);
                            }
                        }
                    }
                    Thread.sleep(1000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String sendGet(String stringUrl) throws Exception {
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        while (responseCode == 429) {
            Thread.sleep(100000);
            responseCode = connection.getResponseCode();
        }
        while (responseCode != 200) {
            Thread.sleep(30000);
            responseCode = connection.getResponseCode();
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
        }
        input.close();
        return response.toString();
    }

}
