package furkanservetkaya.complex.types;

import java.sql.Date;

public class SatisContractComplex {

	private int id;
	private String musteriAdi;
	private String personelAdi;
	private String urunAdi;
	private int adet;
	private String tarih;
	
	public int getId() {
		return id;
	}
	public String getMusteriAdi() {
		return musteriAdi;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public String getUrunAdi() {
		return urunAdi;
	}
	public int getAdet() {
		return adet;
	}
	public String getTarih() {
		return tarih;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMusteriAdi(String musteriAdi) {
		this.musteriAdi = musteriAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public Object[] getVeriler() {
		Object[] veriler= {id,musteriAdi,personelAdi,urunAdi,adet,tarih};
		
		return veriler;
	}
	public String toString() {
		// TODO Auto-generated method stub
		return musteriAdi + " " + personelAdi + " " + urunAdi;
		}

}
