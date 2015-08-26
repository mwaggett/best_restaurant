import org.sql2o.*;
import java.util.List;

public class Cuisine {

   private int id;
   private String name;

  public Cuisine (String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getCuisineName() {
    return name;
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT * FROM restaurants WHERE cuisine_id = :id";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetch(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object otherCuisine) {
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getCuisineName().equals(newCuisine.getCuisineName()) &&
              this.getId() == newCuisine.getId();
    }
  }

  public static List<Cuisine> all() {
    String sql = "SELECT * FROM cuisines";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO cuisines (name) VALUES (:name);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int)con.createQuery(sql, true)
                  .addParameter("name", name)
                  .executeUpdate()
                  .getKey();
    }
  }

  public static Cuisine find(int id) {
    String sql = "SELECT * FROM cuisines WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      Cuisine cuisine = con.createQuery(sql)
                            .addParameter("id", id)
                            .executeAndFetchFirst(Cuisine.class);
      return cuisine;
    }
  }

  public void update( String new_name) {
    this.name = new_name;
    String sql = "UPDATE cuisines SET name = :name WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM cuisines WHERE id = :id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }

  }

}
