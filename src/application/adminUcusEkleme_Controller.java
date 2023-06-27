package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;
import java.util.ResourceBundle;

import com.IsteUcus.Util.UcusveritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class adminUcusEkleme_Controller {
	public adminUcusEkleme_Controller() {
		baglanti=UcusveritabaniUtil.Baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker Tarih_Picker;

    @FXML
    private Button btn_Ekle;

    @FXML
    private Button btn_Guncelle;
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    private Button btn_Sil;
    
    @FXML
    private TableView<VeriGetir> tableView_kayit;

    @FXML
    private TableColumn<VeriGetir, Integer> col_ID;

    @FXML
    private TableColumn<VeriGetir, String> col_Nereden;

    @FXML
    private TableColumn<VeriGetir, String> col_Nereye;

    @FXML
    private TableColumn<VeriGetir, Date> col_Tarih;
    
    @FXML
    private TableColumn<VeriGetir, Integer> col_koltukid;
    
    @FXML
    private TextField txt_Nereden;

    @FXML
    private TextField txt_Nereye;
    
    @FXML
    private TextField txt_koltukid;

    
    @FXML
    void txt_Nereden_Arama(KeyEvent event) {
    	
    }

    public void VerileriGetir(TableView tablo) {
    	
    	sql="select * from ucusekleme";
    	ObservableList<VeriGetir> verilerliste=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				verilerliste.add(new VeriGetir(getirilen.getInt("ucusID"), getirilen.getString("Nereden"), getirilen.getString("Nereye"), getirilen.getDate("kalkis_Tarih"), getirilen.getInt("koltukID")));
			}
			col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
			col_Nereden.setCellValueFactory(new PropertyValueFactory<>("Nerede"));
			col_Nereye.setCellValueFactory(new PropertyValueFactory<>("Nerey"));
			col_Tarih.setCellValueFactory(new PropertyValueFactory<>("kalkis_Tari"));
			col_koltukid.setCellValueFactory(new PropertyValueFactory<>("koltukid"));
			tableView_kayit.setItems(verilerliste);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    
    
    @FXML
    void tableVİewMouseClik(MouseEvent event) {
    	VeriGetir kayit=new VeriGetir();
    	kayit=(VeriGetir) tableView_kayit.getItems().get(tableView_kayit.getSelectionModel().getSelectedIndex());
    	txt_Nereden.setText(kayit.getNerede());
    	txt_Nereye.setText(kayit.getNerey());
    	txt_koltukid.setText(String.valueOf(kayit.getKoltukid()));
    	Tarih_Picker.setPromptText(String.valueOf(kayit.getKalkis_Tari()));
    	
    }

    @FXML
    void Tarih_PickerClick(ActionEvent event) {
    	
    }

    @FXML
    void btn_EkleClick(ActionEvent event) {
    	sql="insert into ucusekleme (Nereden, Nereye, kalkis_Tarih, koltukID) values(?,?,?,?)";
    	String[] satirlar = {
    		    txt_Nereden.getText().trim(),
    		    txt_Nereye.getText().trim(),
    		    String.valueOf(Tarih_Picker.getValue()).trim(),
    		    String.valueOf(txt_koltukid.getText().trim())
    	};
    	application.fonksiyonlar.getInstance().tabloyaEkleme(sql, satirlar);
    	VerileriGetir(tableView_kayit);	
    }

    @FXML
    void btn_GuncelleClick(ActionEvent event) {
    	sql="update ucusekleme set Nereden=?, Nereye=?, koltukID=? where kalkis_Tarih=?";
    	String[] satirlar = {
    		    txt_Nereden.getText().trim(),
    		    txt_Nereye.getText().trim(),
    		    String.valueOf(txt_koltukid.getText().trim()),
    		    String.valueOf(Tarih_Picker.getPromptText()).trim()
    		    
    	};
    	application.fonksiyonlar.getInstance().tabloyaEkleme(sql, satirlar);
    	VerileriGetir(tableView_kayit);
    }

    @FXML
    void btn_SilClick(ActionEvent event) {
    	sql="delete from ucusekleme where Nereden=? and Nereye=? and kalkis_Tarih=? and koltukID=?";
    	String[] satirlar = {
    		    txt_Nereden.getText().trim(),
    		    txt_Nereye.getText().trim(),
    		    String.valueOf(Tarih_Picker.getValue()).trim(),
    		    String.valueOf(txt_koltukid.getText().trim())
    	};
    	application.fonksiyonlar.getInstance().tabloyaEkleme(sql, satirlar);
    	VerileriGetir(tableView_kayit);
    }

    @FXML
    void initialize() {
    	
     	VerileriGetir(tableView_kayit);
     	
    }

}
