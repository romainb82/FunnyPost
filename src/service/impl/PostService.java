package service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import service.IPostService;
import entite.IBaseDonnee;
import entite.IPost;
import entite.impl.BaseDonnee;
import entite.impl.Post;
import entite.impl.RequestData;
import entite.IRequestData;

public class PostService implements IPostService {

	IBaseDonnee bdd = new BaseDonnee();
	IRequestData requestData;

	@Override
	public void getConnection() throws Exception {
		requestData = new RequestData(bdd.initConnection());
	}

	@Override
	public List<IPost> getPostBdd(String request) {
		try {
			return requestData.selectPost(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IPost> getPostBddByTitle(String request) {
		try {
			return requestData.selectPostByTitle(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getURL() {
		try {

			URL url = new URL("https://jsonplaceholder.typicode.com/posts");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setConnectTimeout(200000);
			connection.setReadTimeout(200000);
			connection.setUseCaches(true);
			connection.setRequestMethod("GET");

			// Set Headers
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestProperty("Content-Type", "application/xml");

			int responseCode = connection.getResponseCode();
			if (responseCode == 400) {
				System.out.println("Client Error !!");
			} else if (responseCode == 500) {
				System.out.println("Server Error !!");

			} else if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder responseBody = new StringBuilder();
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					responseBody.append(inputLine).append("\n");
				}

				in.close();

				JSONParser parser = new JSONParser();

				try {
					JSONArray jsonArray = (JSONArray) parser.parse(responseBody.toString());

					for (Object obj : jsonArray) {
						JSONObject jsonObject = (JSONObject) obj;

						IPost post = new Post();
						post.setIdPost(jsonObject.get("id").toString());
						post.setTitle((String) jsonObject.get("title"));
						post.setBody((String) jsonObject.get("body"));
						if(post != null) {
							requestData.insertPost(post);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void deletePost(String id) {
		requestData.deletePostById(id);
	}
}
