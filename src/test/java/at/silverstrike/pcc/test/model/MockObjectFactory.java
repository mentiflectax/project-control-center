package at.silverstrike.pcc.test.model;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyLimitResourceAllocation;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;

public class MockObjectFactory {
	
	public final UserData createUserData() {
		return new MockUserData();
	}

	public final ControlProcess createControlProcess() {
		return new MockControlProcess();
	}
	
	public final Booking createBooking() {
		return new MockBooking();
	}
	
	public final DailyLimitResourceAllocation createDailyLimitResourceAllocation() {
		return new MockDailyLimitResourceAllocation();
	}
	
	public final DailyPlan createDailyPlan() {
		return new MockDailyPlan();
	}
	
	public final DailySchedule createDailySchedule() {
		return new MockDailySchedule();
	}
	
	public final DailyToDoList createDailyToDoList() {
		return new MockDailyToDoList();
	}
	
	public final Resource createResource() {
		return new MockResource();
	}
	
	public final ResourceAllocation createResourceAllocation() {
		return new MockResourceAllocation();
	}
	
	public final Worker createWorker() {
		return new MockWorker();
	}
}