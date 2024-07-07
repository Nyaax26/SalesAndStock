package furkanservetkaya.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import furkanservetkaya.core.ObjectHelper;
import furkanservetkaya.interfaces.DALInterfaces;
import furkanservetkaya.types.MusteriContract;
import furkanservetkaya.types.UrunlerContract;

public class MusteriDAL extends ObjectHelper implements DALInterfaces<MusteriContract> {

	@Override
	public void Insert(MusteriContract entity) {

		Connection connection = getConneciton();
		
		try {
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("INSERT INTO Musteri (AdiSoyadi, Telefon, Adres) " 
			+ "VALUES('" 
			+ entity.getAdiSoyadi() 
			+ "','"+entity.getTelefon()
			+"','"+entity.getAdres()
			+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MusteriContract> GetAll() {
		List<MusteriContract> datacontract = new ArrayList<MusteriContract>();
		Connection connection = getConneciton();
		MusteriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Musteri");
			while (resultSet.next()) {
				contract = new MusteriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				contract.setAdres(resultSet.getString("Adres"));
				//contract.setSehirId(resultSet.getString("SehirId"));
				contract.setTelefon(resultSet.getString("Telefon"));
			
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public MusteriContract Delete(MusteriContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Musteri WHERE Id = " + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public void Update(MusteriContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE Musteri SET AdiSoyadi='" + entity.getAdiSoyadi() + "', " +
                           "Telefon=" + entity.getTelefon() + ", " +
                           "Adres='" + entity.getAdres() + 
                           " WHERE Id=" + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<MusteriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<MusteriContract> GetSearchMusteri(String MusteriAdi) {
		
		List<MusteriContract> dataContart = new ArrayList<MusteriContract>();
		
		Connection connection = getConneciton();
		try {
			Statement statment = connection.createStatement();
			ResultSet rs = statment.executeQuery("SELECT *FROM Musteri WHERE adiSoyadi LIKE '"+"%"+MusteriAdi+"%"+"'");
			while(rs.next()) {
				MusteriContract contract = new MusteriContract();
				
				contract.setAdiSoyadi(rs.getString("adiSoyadi"));
				dataContart.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContart;
		}
	}

