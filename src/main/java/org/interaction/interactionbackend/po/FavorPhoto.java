package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FavorPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "url", nullable = false)
    private String url;

    public FavorPhoto(Integer userId, String url) {
        this.userId = userId;
        this.url = url;
    }

    public FavorPhoto() {}
}
