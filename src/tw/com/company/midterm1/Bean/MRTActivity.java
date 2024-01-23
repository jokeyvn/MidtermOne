package tw.com.company.midterm1.Bean;

import java.sql.Date;

import tw.com.company.midterm1.Util.MRTActivityUtil;

public class MRTActivity {
	private int ID;
	private Date ActivityDate;
	private Date ActivityEnd;
	private String Place;
	private String Activity;
	private String Organizer;
	
	public MRTActivity() {}
	
	public MRTActivity(String ActivityDate,String ActivityEnd,String Place,String Activity,String Organizer) {
		this.ActivityDate = MRTActivityUtil.StringToDate(ActivityDate);
		this.ActivityEnd = MRTActivityUtil.StringToDate(ActivityEnd);
		this.Activity = Activity;
		this.Place = Place;
		this.Organizer = Organizer;
	}
	
	public MRTActivity(int id,Date ActivityDate,Date ActivityEnd,String Place,String Activity,String Organizer) {
		this.ID = id;
		this.ActivityDate = ActivityDate;
		this.ActivityEnd = ActivityEnd;
		this.Activity = Activity;
		this.Place = Place;
		this.Organizer = Organizer;
	}
	
	public MRTActivity(String column,String content) {
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public Date getActivityDate() {
		return ActivityDate;
	}

	public void setActivityDate(Date activityDate) {
		ActivityDate = activityDate;
	}

	public Date getActivityEnd() {
		return ActivityEnd;
	}

	public void setActivityEnd(Date activityEnd) {
		ActivityEnd = activityEnd;
	}

	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		Activity = activity;
	}
	public String getOrganizer() {
		return Organizer;
	}
	public void setOrganizer(String organizer) {
		Organizer = organizer;
	}
	
	public String toString() {
		return ID+" "+ActivityDate+" "+ActivityEnd+" "+Place+" "+Activity+" "+Organizer;
	}
}
