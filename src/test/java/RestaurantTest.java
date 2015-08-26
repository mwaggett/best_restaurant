import org.junit.*;
import static org.junit.Assert.*;
// import anything else needed

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Restaurant.all().size());
  }

  @Test
  public void equals_returnTrueIfNamesMatch() {
    Restaurant firstRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    Restaurant secondRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    assertEquals(true, firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void getRestaurantName_returnsCorrectName(){
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    assertEquals("McDonalds", newRestaurant.getRestaurantName());
  }

  @Test
  public void getDescription_returnsCorrectDescription(){
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    assertEquals("Fast food burgers.", newRestaurant.getDescription());
  }

  @Test
  public void getCuisineId_returnsCorrectId(){
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    assertEquals(1, newRestaurant.getCuisineId());
  }

  @Test
  public void getId_returnsCorrectValue() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    assertEquals(Restaurant.all().get(0).getId(), newRestaurant.getId());
  }

  @Test
  public void save_addsRestaurantToDatabase() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    assertEquals(true, Restaurant.all().get(0).equals(newRestaurant));
  }

  @Test
  public void find_findsRestaurantById() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(newRestaurant.getId());
    assertEquals(savedRestaurant, newRestaurant);
  }

  @Test
  public void updateName_updatesToNewName() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    newRestaurant.updateName("Burger King");
    assertEquals("Burger King",Restaurant.all().get(0).getRestaurantName());
  }

  @Test
  public void updateDescription_updatesToNewDescription() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    newRestaurant.updateDescription("Quality burgers really fast.");
    assertEquals("Quality burgers really fast.",Restaurant.all().get(0).getDescription());
  }

  @Test
  public void updateCuisine_updatesToNewCuisine() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    newRestaurant.updateCuisine(3);
    assertEquals(3, Restaurant.all().get(0).getCuisineId());
  }

  @Test
  public void delete_cuisine() {
    Restaurant newRestaurant = new Restaurant("McDonalds", "Fast food burgers.", 1);
    newRestaurant.save();
    newRestaurant.delete();
    assertEquals(0,Restaurant.all().size());
  }

}
