package service;

import java.util.HashMap;
import java.util.List;

import entite.IPost;

public interface IPostService {
	public void getConnection() throws Exception;
	
	public List<IPost> getPostBdd(String request);
	
	public List<IPost> getPostBddByTitle(String request);
	
	public void getURL();
	
	public void deletePost(String id);
}
