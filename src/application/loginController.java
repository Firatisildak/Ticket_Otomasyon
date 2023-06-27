package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


import com.IsteUcus.Util.UcusveritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class loginController {
	public loginController() {
		baglanti=UcusveritabaniUtil.Baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btn_Kayit;
    
    @FXML
    private Button btn_Giris;

    @FXML
    private TextField kul_Ad;
    
    @FXML
    private Stage loginStage;

    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    private TextField kul_Sifre;
    
    @FXML
    void btn_Giris_Click(ActionEvent event) {
    	LoginKontrol(kul_Ad.getText(), kul_Sifre.getText(), event);
    	
    }
    
    @FXML
    void btn_Kayit_Click(ActionEvent event) {
    	String[] satirlar = new String[3]; 
        satirlar[0] = kul_Ad.getText().trim(); 
        satirlar[1] = UcusveritabaniUtil.MDSifrele(kul_Sifre.getText().trim()); 
        satirlar[2] = "0";
        sql="insert into login (kul_ad, sifre, yetki) values(?,?,?)";
        fonksiyonlar fonksiyonlar = application.fonksiyonlar.getInstance();
        fonksiyonlar.tabloyaEkleme(sql, satirlar);
    }
    
    public <HttpSession> void LoginKontrol(String kul, String sifre, ActionEvent event) {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Fırat Bilet");
    	alert.setHeaderText("Hata mesajı");

    	if(!kul.isEmpty() && !sifre.isEmpty()) {
    		sql="select * from login where kul_ad=? and sifre=?";
    		
        	try {
    			sorguIfadesi=baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, kul_Ad.getText().trim());
    			sorguIfadesi.setString(2, UcusveritabaniUtil.MDSifrele(kul_Sifre.getText().trim()));
    			
    			
    			ResultSet getirilen=sorguIfadesi.executeQuery();
    			
    			if(!getirilen.next()) {
    				alert.setContentText("Kullanıcı adı veya şifre hatalı...");
    			}else {
    				int yetki = getirilen.getInt("yetki");
    				if(yetki == 1) {
    					
    					
    					FXMLLoader loader = new FXMLLoader(getClass().getResource("adminUcusEkleme.fxml"));
        			    Parent root = loader.load();
        			    Stage newStage = new Stage();
        			    newStage.setScene(new Scene(root));
        			    newStage.show();
        			    fonksiyonlar.sayfaKapatma(event);
    				}else {
    					FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));
        			    Parent root = loader.load();
        			    Stage newStage = new Stage();
        			    newStage.setScene(new Scene(root));
        			    newStage.show();
        			    fonksiyonlar.sayfaKapatma(event);
    				}
     
    			}
    			
    		} catch (Exception e) {
    			// TODO: handle exception
    			alert.setContentText(e.getMessage().toString());
    		}
    	}else {
    		alert.setContentText("Kullanıcı adı ve şifre boş geçilemez");
    	}
    	alert.showAndWait();
    }
    @FXML
    void initialize() {
       
    }

}
