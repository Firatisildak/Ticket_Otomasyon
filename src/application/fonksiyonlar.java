package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.IsteUcus.Util.UcusveritabaniUtil;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class fonksiyonlar {
    private static fonksiyonlar instance;
    private Connection baglanti;
    private PreparedStatement sorguIfadesi;
    private ResultSet getirilen;

    private fonksiyonlar() {
        baglanti = UcusveritabaniUtil.Baglan();
    }
    public static fonksiyonlar getInstance() {
        if (instance == null) {
            instance = new fonksiyonlar();
        }
        return instance;
    }

    public void tabloyaEkleme(String sql, String[] satirlar) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fırat Bilet");
        alert.setHeaderText("Hata mesajı");
        
        try {
            sorguIfadesi = baglanti.prepareStatement(sql);
            int i = 1;
            for (String satir : satirlar) {
                sorguIfadesi.setString(i, satir);
                i++;
            }
            sorguIfadesi.executeUpdate();
        } catch (Exception e) {
            alert.setContentText(e.getMessage().toString());
        }
    }
    public static void sayfaKapatma(ActionEvent event) {
		try {
			 Node source =(Node)event.getSource();
	         Scene currentScene=source.getScene();
	         Stage currentStage=(Stage)currentScene.getWindow();
	         currentStage.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
