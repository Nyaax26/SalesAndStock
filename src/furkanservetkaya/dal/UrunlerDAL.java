package furkanservetkaya.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import furkanservetkaya.core.ObjectHelper;
import furkanservetkaya.interfaces.DALInterfaces;
import furkanservetkaya.types.UrunlerContract;

public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		
		Connection connection = getConneciton();
	    try {
	        Statement statement = connection.createStatement();
	        String query = "INSERT INTO Urunler(Adi, KategoriId, Tarih, Fiyat) " +
	                       "VALUES ('" + entity.getAdi() + "', " +
	                       entity.getKategoriId() + ", '" +
	                       entity.getTarih() + "', " +
	                       entity.getFiyat() + ")";
	        statement.executeUpdate(query);
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		
	}

	@Override
	public List<UrunlerContract> GetAll() {
		List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
		Connection connection = getConneciton();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
			while (resultSet.next()) {
				contract = new UrunlerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setKategoriId(resultSet.getInt("KategoriId"));
				contract.setTarih(resultSet.getString("Tarih"));
				datacontract.add(contract);
				System.out.println(resultSet.getString("Adi"));
									
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public UrunlerContract Delete(UrunlerContract entity) {
		 Connection connection = getConneciton();
	        try {
	            Statement statement = connection.createStatement();
	            String query = "DELETE FROM Urunler WHERE Id = " + entity.getId();
	            statement.executeUpdate(query);
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return entity;
	}

	@Override
	public void Update(UrunlerContract entity) {
		 Connection connection = getConneciton();
	        try {
	            Statement statement = connection.createStatement();
	            String query = "UPDATE Urunler SET Adi='" + entity.getAdi() + "', " +
	                           "KategoriId=" + entity.getKategoriId() + ", " +
	                           "Tarih='" + entity.getTarih() + "', " +
	                           "Fiyat=" + entity.getFiyat() + 
	                           " WHERE Id=" + entity.getId();
	            statement.executeUpdate(query);
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UrunlerContract> GetSearchUrunler(String UrunlerAdi) {
	
	List<UrunlerContract> dataContart = new ArrayList<UrunlerContract>();
	
	Connection connection = getConneciton();
	try {
		Statement statment = connection.createStatement();
		ResultSet rs = statment.executeQuery("SELECT *FROM Urunler WHERE Adi LIKE '"+"%"+UrunlerAdi+"%"+"'");
		while(rs.next()) {
			UrunlerContract contract = new UrunlerContract();
			
			contract.setAdi(rs.getString("Adi"));
			contract.setKategoriId(rs.getInt("KategoriId"));
			dataContart.add(contract);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return dataContart;
	}
}
