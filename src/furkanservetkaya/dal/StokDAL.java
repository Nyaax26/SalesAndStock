package furkanservetkaya.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import furkanservetkaya.complex.types.StokContractComplex;
import furkanservetkaya.complex.types.StokContractTotalComplex;
import furkanservetkaya.core.ObjectHelper;
import furkanservetkaya.interfaces.DALInterfaces;
import furkanservetkaya.types.StokContract;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {
		Connection connection = getConneciton();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Stok (personelId,urunId,tarih,adet) VALUES("
			+entity.getPersonelId()
			+","
			+entity.getUrunId()
			+",'"
			+entity.getTarih()
			+"',"
			+entity.getAdet() 
			+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * SELECT stok.Id,AdiSoyadi,Adi Adet,stok.Tarih FROM stok 
	 * LEFT JOIN urunler on stok.UrunId = urunler.Id 
	 * LEFT JOIN personel on stok.PersonelId = personel.Id;
	 */
	public List <StokContractComplex> GetAllStok(){
	List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
	Connection connection = getConneciton();
	StokContractComplex contract;
	try {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT stok.Id, AdiSoyadi, Adi, Adet,stok.Tarih FROM stok"
				+ " LEFT JOIN urunler on stok.UrunId = urunler.Id "
				+ " LEFT JOIN personel on stok.PersonelId = personel.Id ORDER BY stok.Id DESC");
		while (resultSet.next()) {
			contract = new StokContractComplex();
			contract.setId(resultSet.getInt("Id"));
			contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
			contract.setUrunAdi(resultSet.getString("urunler.Adi"));
			contract.setAdet(resultSet.getInt("Adet"));
			contract.setTarih(resultSet.getString("stok.Tarih"));
			
			datacontract.add(contract);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return datacontract;
}
	public List <StokContractTotalComplex> GetTotalStok(){
		List<StokContractTotalComplex> datacontract = new ArrayList<StokContractTotalComplex>();
		Connection connection = getConneciton();
		StokContractTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT SUM(Adet) as toplam, stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM stok "
					+ "LEFT JOIN urunler on stok.UrunId = urunler.Id "
					+ "LEFT JOIN personel on stok.PersonelId = personel.Id "
					+ "GROUP BY UrunId ORDER BY toplam desc");
			while (resulset.next()) {
							contract = new StokContractTotalComplex();
							contract.setId(resulset.getInt("Id"));
							contract.setPersonelAdi(resulset.getString("AdiSoyadi"));
							contract.setUrunAdi(resulset.getString("urunler.Adi"));
							contract.setAdet(resulset.getInt("Adet"));
							contract.setTarih(resulset.getString("stok.Tarih"));	
							contract.setToplam(resulset.getInt("Toplam"));
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
