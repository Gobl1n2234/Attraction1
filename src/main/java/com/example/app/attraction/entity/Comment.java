package com.example.app.attraction.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Сущность Комментария
 */
@Entity
@Setter
@Getter
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;


    @Column(name = "date", updatable = false, nullable = false)
    private LocalDateTime date;


    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Attraction attraction;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(userId, comment.userId) && Objects.equals(date, comment.date) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, text);
    }

    @CreationTimestamp
    protected void onCreate()
    {
        this.date = LocalDateTime.now();
    }
}