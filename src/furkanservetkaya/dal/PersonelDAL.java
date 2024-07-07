package furkanservetkaya.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import furkanservetkaya.core.ObjectHelper;
import furkanservetkaya.interfaces.DALInterfaces;
import furkanservetkaya.types.PersonelContract;
import furkanservetkaya.types.UrunlerContract;


public class PersonelDAL extends ObjectHelper implements DALInterfaces<PersonelContract>{

	@Override
	public void Insert(PersonelContract entity) {
		
		Connection connection = getConneciton();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Personel (AdiSoyadi,Email) "
					+ "VALUES('"
					+entity.getAdiSoyadi() 
					+"','"
					+entity.getEmail()
					+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<PersonelContract> GetAll() {
		
		List<PersonelContract> datacontract = new ArrayList<PersonelContract>();
		Connection connection = getConneciton();
		PersonelContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Personel");
			while (resultSet.next()) {
				contract = new PersonelContract();
				
				contract.setId(resultSet.getInt("Id"));
				contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				contract.setEmail(resultSet.getString("Email"));
				
				
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public PersonelContract Delete(PersonelContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Personel WHERE Id = " + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public void Update(PersonelContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE Personel SET AdiSoyadi='" + entity.getAdiSoyadi() + "', " +
                           "Email=" + entity.getEmail() +           
                           " WHERE Id=" + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<PersonelContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

public List<PersonelContract> GetSearchPersonel(String PersonelAdi) {
	
	List<PersonelContract> dataContart = new ArrayList<PersonelContract>();
	
	Connection connection = getConneciton();
	try {
		Statement statment = connection.createStatement();
		ResultSet rs = statment.executeQuery("SELECT *FROM Personel WHERE email LIKE '"+"%"+PersonelAdi+"%"+"'");
		while(rs.next()) {
			PersonelContract contract = new PersonelContract();
			
			contract.setEmail(rs.getString("email"));
			dataContart.add(contract);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return dataContart;
	}
}