import org.junit.*;
import static org.junit.Assert.*;
// import anything else needed

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Cuisine.all().size());
  }

  @Test
  public void equals_returnTrueIfNamesMatch() {
    Cuisine firstCuisine = new Cuisine("Italian");
    Cuisine secondCuisine = new Cuisine("Italian");
    assertEquals(true, firstCuisine.equals(secondCuisine));
  }

  @Test
  public void getCuisineName_returnsCorrectName(){
    Cuisine newCuisine = new Cuisine("Italian");
    assertEquals("Italian", newCuisine.getCuisineName());
  }

  @Test
  public void getId_returnsCorrectValue() {
    Cuisine newCuisine = new Cuisine("Italian");
    newCuisine.save();
    assertEquals(Cuisine.all().get(0).getId(), newCuisine.getId());
  }

  @Test
  public void save_addsCuisineToDatabase() {
    Cuisine newCuisine = new Cuisine("Italian");
    newCuisine.save();
    assertEquals(true, Cuisine.all().get(0).equals(newCuisine));
  }

  @Test
  public void find_findsCuisineById() {
    Cuisine newCuisine = new Cuisine("Italian");
    newCuisine.save();
    Cuisine savedCuisine = Cuisine.find(newCuisine.getId());
    assertEquals(savedCuisine, newCuisine);
  }

  @Test
  public void update_cuisine() {
    Cuisine newCuisine = new Cuisine("Italian");
    newCuisine.save();
    newCuisine.update("Indian");
    assertEquals("Indian",Cuisine.all().get(0).getCuisineName());
  }

  @Test
  public void delete_cuisine() {
    Cuisine newCuisine = new Cuisine("Italian");
    newCuisine.save();
    newCuisine.delete();
    assertEquals(0,Cuisine.all().size());
  }






}
