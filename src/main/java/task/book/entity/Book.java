package task.book.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.Range;
import task.validation.ValidationBookGroup;
import task.validation.ValidationPropositionGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {ValidationPropositionGroup.class, ValidationBookGroup.class})
    @Size(min = 5, groups = ValidationBookGroup.class)
    private String title;

    @NotNull(groups = ValidationBookGroup.class)
    @Range(min = 0, max = 10, groups = ValidationBookGroup.class)
    private Integer rating;

    @NotBlank
    @Size(max = 600)
    private String description;

    @Min(value = 1, groups = ValidationBookGroup.class)
    private Integer pages;


    private boolean proposition;

    @NotNull(groups = ValidationBookGroup.class)
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    @NotNull(groups = ValidationBookGroup.class)
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book() {
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", pages=" + pages +
//                ", authors=" + authors +
                ", publisher=" + publisher +
                '}';
    }

    public boolean isProposition() {
        return proposition;
    }

    public void setProposition(boolean proposition) {
        this.proposition = proposition;
    }
}
