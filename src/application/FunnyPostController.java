package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import entite.IPost;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import service.IPostService;
import service.impl.PostService;

public class FunnyPostController implements Initializable {

    @FXML
    private Label body;

    @FXML
    private Button initBtn;
    
    @FXML
    private Button deleteBtn;

    @FXML
    private TextField deleteInput;
    
    @FXML
    private ListView<String> listTitle;
    
    private String currentTitle;
    IPostService postService;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			postService = new PostService();
		
			postService.getConnection();
			
			UpdateListView();		

		    listTitle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					currentTitle = listTitle.getSelectionModel().getSelectedItem();

					String request = "SELECT * FROM Post WHERE title = \'"+ currentTitle.split(" - ")[1] +"\'";
					List<IPost> postByTitle = postService.getPostBddByTitle(request);
					body.setText(postByTitle.get(0).getBody());
				}
			});	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void UpdateListView() {
		String request = "SELECT * FROM Post";

		List<IPost> postArray = postService.getPostBdd(request);
		
		listTitle.getItems().clear();

		for (int i = 0; i < postArray.size(); i++) {
			listTitle.getItems().addAll(postArray.get(i).getIdPost() + " - " + postArray.get(i).getTitle());
		}
	}
	
	@FXML
	public void onClickInit() {
		postService.getURL();

		UpdateListView();	
	}
	
	@FXML
	public void onClickDelete() {
		String idEnter = deleteInput.getText();
		postService.deletePost(idEnter);
		
		UpdateListView();
		deleteInput.setText("");
	}
}
