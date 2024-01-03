package entite.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entite.IPost;
import entite.IRequestData;

public class RequestData implements IRequestData{
	
	java.sql.Connection conn;	
	
	public RequestData(java.sql.Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<IPost> selectPost(String rst) {
	    try {
	        List<IPost> postList = new ArrayList<>();

	        try (Statement statement = this.conn.createStatement(); ResultSet resultSet = statement.executeQuery(rst)) {
	            while (resultSet.next()) {
	                String id = resultSet.getString("id");
	                String title = resultSet.getString("title");
	                String body = resultSet.getString("body");
	                
	                IPost newPost = new Post(id, title, body);
	                postList.add(newPost);
	            }
	            return postList;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public List<IPost> selectPostByTitle(String rst) {
	    try {
	        List<IPost> postList = new ArrayList<>();

	        try (Statement statement = this.conn.createStatement(); ResultSet resultSet = statement.executeQuery(rst)) {
	            while (resultSet.next()) {
	                String id = resultSet.getString("id");
	                String title = resultSet.getString("title");
	                String body = resultSet.getString("body");
	                
	                IPost newPost = new Post(id, title, body);
	                postList.add(newPost);
	            }
	            return postList;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public void insertPost(IPost post) {
	    try {
	        String insertQuery = "INSERT INTO Post (title, body) VALUES (?, ?)";
	        try (PreparedStatement preparedStatement = this.conn.prepareStatement(insertQuery)) {
	            preparedStatement.setString(1, post.getTitle());
	            preparedStatement.setString(2, post.getBody());

	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Insertion réussie !");
	            } else {
	                System.out.println("Échec de l'insertion.");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deletePostById(String postId) {
        try {
            String deleteQuery = "DELETE FROM Post WHERE id = ?";
            try (PreparedStatement preparedStatement = this.conn.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, postId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Suppression réussie !");
                } else {
                    System.out.println("Aucune ligne supprimée. L'id n'existe peut-être pas.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public void addPost(IPost post) {
        try {
            String insertQuery = "INSERT INTO Post (title, body) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = this.conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, post.getTitle());
                preparedStatement.setString(2, post.getBody());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ajout de post réussi !");
                } else {
                    System.out.println("Échec de l'ajout de post.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	@Override
    public void updatePost(IPost post) {
        try {
            String updateQuery = "UPDATE Post SET title = ?, body = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, post.getTitle());
                preparedStatement.setString(2, post.getBody());
                preparedStatement.setString(3, post.getIdPost());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Modification de post réussie !");
                } else {
                    System.out.println("Aucune ligne modifiée. L'id n'existe peut-être pas.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
