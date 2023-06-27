package application;

import java.util.Date;

public class VeriGetir {
	
	private int ID;
	private String Nerede;
	private String Nerey;
	private Date kalkis_Tari;
	private int koltukid;
	
	
	VeriGetir(){
		
	}
	VeriGetir(int ID, String Nerede, String Nerey, Date kalkis_Tari, int koltukid){
		this.ID=ID;
		this.Nerede=Nerede;
		this.Nerey=Nerey;
		this.kalkis_Tari=kalkis_Tari;
		this.koltukid=koltukid;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNerede() {
		return Nerede;
	}
	public void setNerede(String nerede) {
		Nerede = nerede;
	}
	public String getNerey() {
		return Nerey;
	}
	public void setNerey(String nerey) {
		Nerey = nerey;
	}
	public Date getKalkis_Tari() {
		return kalkis_Tari;
	}
	public void setKalkis_Tari(Date kalkis_Tari) {
		this.kalkis_Tari = kalkis_Tari;
	}
	public int getKoltukid() {
		return koltukid;
	}
	public void setKoltukid(int koltukid) {
		this.koltukid = koltukid;
	}
}
