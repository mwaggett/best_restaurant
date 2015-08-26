import org.sql2o.*;
import java.util.List;

public class Restaurant {

   private int id;
   private String name;
   private String description;
   private int cuisine_id;

  public Restaurant (String name, String description, int cuisine_id) {
    this.name = name;
    this.description = description;
    this.cuisine_id = cuisine_id;
  }

  public int getId() {
    return id;
  }

  public String getRestaurantName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getCuisineId() {
    return cuisine_id;
  }


  @Override
  public boolean equals(Object otherRestaurant) {
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getRestaurantName().equals(newRestaurant.getRestaurantName()) &&
              this.getId() == newRestaurant.getId();
    }
  }

  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO restaurants (name, description, cuisine_id) VALUES (:name,:description,:cuisine_id);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
                  .addParameter("name", name)
                  .addParameter("description", description)
                  .addParameter("cuisine_id", cuisine_id)
                  .executeUpdate()
                  .getKey();
    }
  }

  public static Restaurant find(int id) {
    String sql = "SELECT * FROM restaurants WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      Restaurant restaurant = con.createQuery(sql)
                            .addParameter("id", id)
                            .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  public void updateName( String new_name) {
    this.name = new_name;
    String sql = "UPDATE restaurants SET name = :name WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDescription( String new_description) {
    this.description = new_description;
    String sql = "UPDATE restaurants SET description = :description WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateCuisine(int new_cuisine_id) {
    this.cuisine_id = new_cuisine_id;
    String sql = "UPDATE restaurants SET cuisine_id = :cuisine_id WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM restaurants WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }

  }

}
