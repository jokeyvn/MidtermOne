package tw.com.company.midterm1.Factory;

import tw.com.company.midterm1.DAO.CreateImageDaoJdbcImpl;

public class CreateImageDaoFactory {
	public static CreateImageDaoJdbcImpl CreateImageDao() {
		return new CreateImageDaoJdbcImpl();
	}
}
