package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.IsteUcus.Util.UcusveritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AnaSayfaController {
	
	public AnaSayfaController() {
		baglanti=UcusveritabaniUtil.Baglan();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ucusAra;

    @FXML
    private TableColumn<VeriGetir, String> col_Nereden;

    @FXML
    private TableColumn<VeriGetir, String> col_Nereye;
    
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    private TableColumn<VeriGetir, Date> col_tarih;

    @FXML
    private DatePicker datepicker_Donus;

    @FXML
    private DatePicker datepicker_Gidis;

    @FXML
    private TableView<VeriGetir> tableview_Ucus;

    @FXML
    private TextField txt_Nereden;

    @FXML
    private TextField txt_Nereye;
   
    @FXML
    void tableVİewMouseClik(MouseEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("koltukSecme.fxml"));
		Parent root = loader.load();
		Stage newStage = new Stage();
		newStage.setScene(new Scene(root));
		newStage.show();
		
    }
    
    @FXML
    void btn_ucusAra_Click(ActionEvent event) {
    	
    	sql="select * from ucusekleme where kalkis_Tarih>'"+datepicker_Gidis.getValue()+"' and kalkis_Tarih<'"+datepicker_Donus.getValue()+"'";
    	VerileriGetir(tableview_Ucus, sql);
    }
    public void VerileriGetir(TableView tablo, String sql) {
    	
    	
    	ObservableList<VeriGetir> verilerliste=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				verilerliste.add(new VeriGetir(getirilen.getInt("ucusID"), getirilen.getString("Nereden"), getirilen.getString("Nereye"), getirilen.getDate("kalkis_Tarih"), getirilen.getInt("koltukID")));
			}
			
			col_Nereden.setCellValueFactory(new PropertyValueFactory<>("Nerede"));
			col_Nereye.setCellValueFactory(new PropertyValueFactory<>("Nerey"));
			col_tarih.setCellValueFactory(new PropertyValueFactory<>("kalkis_Tari"));
			tableview_Ucus.setItems(verilerliste);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    
    @FXML 
    void txt_Nereye_Arama(KeyEvent event) {
    	if(txt_Nereye.getText().equals("")) {
    		sql="select * from ucusekleme";
    	}else {
    		sql="select * from ucusekleme where Nereye like '%"+txt_Nereye.getText()+"%'";
    	}
    	VerileriGetir(tableview_Ucus, sql);
    }
    
    @FXML
    void txt_Nereden_Arama(KeyEvent event) {
    	
    	if(txt_Nereden.getText().equals("")) {
    		sql="select * from ucusekleme";
    	}else {
    		sql="select * from ucusekleme where Nereden like '%"+txt_Nereden.getText()+"%'";
    	}
    	VerileriGetir(tableview_Ucus, sql);
    }
    
    @FXML
    void initialize() {
    	
    	sql="select * from ucusekleme";
    	VerileriGetir(tableview_Ucus, sql);
    	datepicker_Gidis.setValue(LocalDate.now());
    	datepicker_Donus.setValue(LocalDate.now());
    }

}
