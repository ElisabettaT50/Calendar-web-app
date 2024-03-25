package calendar.example.calendar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private String color;
    @OneToMany(mappedBy = "calendar",cascade = CascadeType.ALL)
    private List<Event> eventList;
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    @JsonIgnore
    private User user;

    public Calendar(Long id, String name, String description, String color, List<Event> eventList, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.eventList = eventList;
        this.user = user;
    }

    public Calendar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
