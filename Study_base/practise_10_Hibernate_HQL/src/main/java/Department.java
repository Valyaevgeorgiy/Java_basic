import jakarta.persistence.*;

public class Department {
    private int id;
    private String title;
    private String description;
    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "director")
    Employee director;

    public Department () {

    }
    public Department (int id, String title, String description, Employee director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
    }
    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
