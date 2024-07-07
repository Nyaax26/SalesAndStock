package furkanservetkaya.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import furkanservetkaya.core.ObjectHelper;
import furkanservetkaya.interfaces.DALInterfaces;
import furkanservetkaya.types.KategoriContract;

public class KategoriDAL extends ObjectHelper implements DALInterfaces<KategoriContract> {

	@Override
	public void Insert(KategoriContract entity) {

		Connection connection = getConneciton();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Kategori (Adi,ParentId) " + " VALUES('" + entity.getAdi() + "','"
					+ entity.getParentId() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetAll() {

		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		Connection connection = getConneciton();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	public List<KategoriContract> GetAllParentId() {

		List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
		Connection connection = getConneciton();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori WHERE parentId=0");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				datacontract.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public KategoriContract Delete(KategoriContract entity) {
		Connection connection = getConneciton();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Kategori WHERE Id = " + entity.getId();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public void Update(KategoriContract entity) {

		Connection connection = getConneciton();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE Kategori SET Adi ='" + entity.getAdi() + "', ParentId="
					+ entity.getParentId() + " WHERE id =" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<KategoriContract> GetSearchKategori(String kategoriAdi) {
		
		List<KategoriContract> dataContart = new ArrayList<KategoriContract>();
		
		Connection connection = getConneciton();
		try {
			Statement statment = connection.createStatement();
			ResultSet rs = statment.executeQuery("SELECT *FROM Kategori WHERE Adi LIKE '"+"%"+kategoriAdi+"%"+"'");
			while(rs.next()) {
				KategoriContract contract = new KategoriContract();
				
				contract.setAdi(rs.getString("Adi"));
				contract.setParentId(rs.getInt("ParentId"));
				dataContart.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContart;
	}
}
