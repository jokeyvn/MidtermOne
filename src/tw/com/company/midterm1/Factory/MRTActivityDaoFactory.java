package tw.com.company.midterm1.Factory;

import tw.com.company.midterm1.DAO.MRTActivityDaoJdbcImpl;

public class MRTActivityDaoFactory {
	public static MRTActivityDaoJdbcImpl createMRTActivityDao() {
		return new MRTActivityDaoJdbcImpl();
	}
}
