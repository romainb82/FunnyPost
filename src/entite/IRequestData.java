package entite;

import java.util.List;

public interface IRequestData {
	public List<IPost> selectPost(String rst);
	
	public List<IPost> selectPostByTitle(String rst);
	
	public void insertPost(IPost post);

	public void deletePostById(String postId);
	
	public void updatePost(IPost post);
	
	public void addPost(IPost post);
}
