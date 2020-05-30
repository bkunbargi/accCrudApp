package tripping.test;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tripped.manager.TrippedManagerImpl;
import com.tripped.model.TripImpl;
import com.tripped.model.TripView;
import com.tripped.service.TripServiceImpl;

@SuppressWarnings("deprecation")
public class TrippinMock {

	@Mock
	TripServiceImpl trippedService;

	@InjectMocks
	TrippedManagerImpl trippedManager;

	@BeforeTest
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test(priority = 1)
	public void testFindById() { // Have multiple methods that are similar that are all testing manager findbyID
		TripImpl trip = getImplItem();
		Long id = (long) 28;

		when(trippedService.findById(anyLong())).thenReturn(trip);
		TripView tripView = trippedManager.findById(id);

		assertNotNull(tripView);
		assertEquals(tripView.getLocation(), "China");
		// Another possible test when mockedservice returns null
	}

	// Removing priorities allow parallel running (if setup)
	@Test(priority = 2)
	public void testFindAllTrips() {
		List<TripImpl> tripImplList = getTripImplList();
		when(trippedService.findAllTrips()).thenReturn(tripImplList);
		List<TripView> returnedList = trippedManager.findAllTrips();
		assertNotNull(returnedList);
		assertEquals(returnedList.size(), tripImplList.size());
		assertNotEquals(returnedList.get(1), tripImplList.get(1));
	}

	@Test(priority = 3)
	public void testSaveTrip() {
		TripImpl testTrip = getImplItem();
		TripView otherTrip = getViewItem();
		when(trippedService.saveTrip(ArgumentMatchers.any(TripImpl.class))).thenReturn(testTrip);
		List<String> tripNamesList = trippedManager.getTripNames(getTripImplList());
		TripImpl trip = trippedManager.saveTrip(otherTrip);

		// Two different tests to test this logic.
		// Whenever logic is introduced, you can probably create a new test based around
		// that.

		if (tripNamesList.contains(otherTrip.getLocation())) {
			assertNull(trip.getLocation());
			assertNull(trip.getDate());
			assertNull(trip.getDuration());
			assertNull(trip.getCost());
		} else {
			assertEquals(testTrip, trippedManager.saveTrip(otherTrip));
		}
	}

	@Test(priority = 4)
	public void testUpdateTrip() {
		TripImpl trip = getImplItem();
		TripView otherTrip = getViewItem();
		when(trippedService.saveTrip(ArgumentMatchers.any(TripImpl.class))).thenReturn(trip);
		assertEquals(trip, trippedManager.updateTrip(otherTrip));
		assertEquals(trip.getCost(), otherTrip.getCost());
	}

	@Test(priority = 5)
	public void testDeleteTripById() {
		doNothing().when(trippedService).deleteTripById(ArgumentMatchers.any(Long.class));
	}

	@Test(priority = 6)
	public void testCopyTrips() {
		List<TripImpl> tripImplList = getTripImplList();
		List<TripView> tripViewList = getTripViewList();
		List<TripView> convertedList = trippedManager.copyTrips(tripImplList);

		assertTrue(convertedList.get(1).equals(tripViewList.get(1)));
		assertNotNull(convertedList);
		assertEquals(convertedList.size(), tripViewList.size());
		assertNotEquals(convertedList.get(1), tripImplList.get(1));

	}

	@Test(priority = 7)
	public void testGetTripNames() {
		List<TripImpl> tripImplList = getTripImplList();
		List<String> tripNamesList = trippedManager.getTripNames(tripImplList);

		assertEquals(tripNamesList.size(), tripImplList.size());
		assertFalse(tripNamesList.get(1).equals(tripImplList.get(1)));
		assertTrue(tripNamesList.get(1).equals(tripImplList.get(1).getLocation()));
	}

	// Only used here, public -> private
	private TripImpl getImplItem() {
		TripImpl trip = new TripImpl();
		java.util.Date date = new java.util.Date("10/08/1992");
		trip.setCost("1000");
		trip.setDate(date);
		trip.setLocation("China");
		trip.setDuration("10 Days");
		return trip;
	}

	private TripView getViewItem() {
		TripView trip = new TripView();
		java.util.Date date = new java.util.Date("10/08/1992");
		trip.setCost("1000");
		trip.setDate(date);
		trip.setLocation("China");
		trip.setDuration("10 Days");
		return trip;
	}

	private List<TripImpl> getTripImplList() {
		List<TripImpl> tripImplList = new ArrayList<>();
		tripImplList.add(new TripImpl((long) 10, "China", "10 days", "1000", new java.util.Date("10/08/1992")));
		tripImplList.add(new TripImpl((long) 10, "France", "10 days", "1000", new java.util.Date("10/08/1992")));
		tripImplList.add(new TripImpl((long) 10, "Mongolia", "10 days", "1000", new java.util.Date("10/08/1992")));
		tripImplList.add(new TripImpl((long) 10, "Spain", "10 days", "1000", new java.util.Date("10/08/1992")));

		return tripImplList;
	}

	private List<TripView> getTripViewList() {
		List<TripView> tripViewList = new ArrayList<>();
		tripViewList.add(new TripView((long) 10, "China", "10 days", "1000", new java.util.Date("10/08/1992")));
		tripViewList.add(new TripView((long) 5, "France", "10 days", "1000", new java.util.Date("10/08/1992")));
		tripViewList.add(new TripView((long) 3, "Mongolia", "10 days", "1000", new java.util.Date("10/08/1992")));
		tripViewList.add(new TripView((long) 25, "Spain", "10 days", "1000", new java.util.Date("10/08/1992")));

		return tripViewList;
	}

}
