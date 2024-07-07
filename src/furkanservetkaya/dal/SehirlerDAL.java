package furkanservetkaya.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import furkanservetkaya.core.ObjectHelper;
import furkanservetkaya.interfaces.DALInterfaces;
import furkanservetkaya.types.SehirlerContract;
import furkanservetkaya.types.UrunlerContract;

public class SehirlerDAL extends ObjectHelper implements DALInterfaces<SehirlerContract> {


	@Override
	public void Insert(SehirlerContract entity) {
		
		Connection connection = getConneciton();
		
		try {
			Statement statment = connection.createStatement();
			statment.executeUpdate("INSERT INTO Sehirler(SehirId) " 
					+ "VALUES('"
					+entity.getSehirId()
					+"')");
			statment.close();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SehirlerContract> GetAll() {
		
		List<SehirlerContract> datacontract = new ArrayList<SehirlerContract>();
		Connection connection = getConneciton();
		SehirlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Sehirler");
			while (resultSet.next()) {
				contract = new SehirlerContract();
				
				contract.setId(resultSet.getInt("Id"));
				contract.setSehirId(resultSet.getString("SehirId"));
				
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public SehirlerContract Delete(SehirlerContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Sehirler WHERE Id = " + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public void Update(SehirlerContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE Sehirler SET SehirId='" + entity.getSehirId() +
                           " WHERE Id=" + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<SehirlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SehirlerContract> GetSearchSehirler(String SehirlerAdi) {
		
		List<SehirlerContract> dataContart = new ArrayList<SehirlerContract>();
		
		Connection connection = getConneciton();
		try {
			Statement statment = connection.createStatement();
			ResultSet rs = statment.executeQuery("SELECT *FROM SehirId WHERE Adi LIKE '"+"%"+SehirlerAdi+"%"+"'");
			while(rs.next()) {
				SehirlerContract contract = new SehirlerContract();
				
				contract.setSehirId(rs.getString("SehirlerId"));
				dataContart.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContart;
		}
	}
