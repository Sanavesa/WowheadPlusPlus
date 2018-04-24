package com.wowhead.core;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class Controller implements Initializable
{
    @FXML
    private Button btnClick;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		System.out.println("hi1");
		
		btnClick.setOnAction(e -> {
			System.out.println("Switching scene");
			
			try
			{
				Parent root = FXMLLoader.load(getClass().getResource("../../../fxml/ComplexApplication_css.fxml"));
				btnClick.getScene().setRoot(root);
//				btnClick.getScene().getStylesheets().add(getClass().getResource("../../../fxml/ComplexApplication.css").toString());
//				String str = getClass().getResource("fxml/ComplexApplication.css").toString();
//				System.out.println(str);
//				btnClick.getScene().getStylesheets().add(str);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
    
    
}
