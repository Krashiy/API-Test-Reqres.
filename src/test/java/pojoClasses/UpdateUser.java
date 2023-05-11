package pojoClasses;

public class UpdateUser {
  private String name;
  private String job;

  public UpdateUser(String name, String job) {
    this.name = name;
    this.job = job;
  }

  public String getName() {
    return name;
  }

  public String getJob() {
    return job;
  }
}
