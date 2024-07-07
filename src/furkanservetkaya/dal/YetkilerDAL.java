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
import furkanservetkaya.types.YetkilerContract;

public class YetkilerDAL extends ObjectHelper implements DALInterfaces<YetkilerContract> {

	@Override
	public void Insert(YetkilerContract entity) {
		
		Connection connection = getConneciton();
		try {
		    Statement statement = connection.createStatement();
		    statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES ('" + entity.getAdi() + "')");
		    statement.close();
		    connection.close();
			} catch (SQLException e) {
		    e.printStackTrace();
								   	}
	} 

	@Override
	public List<YetkilerContract> GetAll() {
		List<YetkilerContract> datacontract = new ArrayList<YetkilerContract>();
		Connection connection = getConneciton();
		YetkilerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Yetkiler");
			while (resultSet.next()) {
				contract = new YetkilerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				
				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datacontract;
	}

	@Override
	public YetkilerContract Delete(YetkilerContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Yetkiler WHERE Id = " + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public void Update(YetkilerContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE Yetkiler SET Adi='" + entity.getAdi() +
                           " WHERE Id=" + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<YetkilerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<YetkilerContract> GetSearchYetkiler(String YetkilerAdi) {
		
		List<YetkilerContract> dataContart = new ArrayList<YetkilerContract>();
		
		Connection connection = getConneciton();
		try {
			Statement statment = connection.createStatement();
			ResultSet rs = statment.executeQuery("SELECT *FROM Yetkiler WHERE Adi LIKE '"+"%"+YetkilerAdi+"%"+"'");
			while(rs.next()) {
				YetkilerContract contract = new YetkilerContract();
				
				contract.setAdi(rs.getString("Adi"));
				dataContart.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContart;
		}
	}
