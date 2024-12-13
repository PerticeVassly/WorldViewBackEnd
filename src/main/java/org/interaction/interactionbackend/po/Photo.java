package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.enums.PhotoTheme;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "theme")
    private PhotoTheme theme;

    @Column(name = "likes") // 这个是photo基础的likes，自行设定
    private Integer likes;

    @Column(name = "views") // 这个是photo基础的views，自行设定
    private Integer views;

    @Column(name = "rankingTag") // 这个是photo基础的rankingTag，自行设定
    private Boolean rankingTag;

    @Column(name = "newTag") // 这个是photo基础的newTag，自行设定
    private Boolean newTag;

    @Column(name = "recommendTag") // 这个是photo基础的recommendTag，自行设定
    private Boolean recommendTag;

    public Photo(Integer userId, String url, String title, String description, PhotoTheme theme) {
        this.userId = userId;
        this.url = url;
        this.title = title;
        this.description = description;
        this.theme = theme;
        this.rankingTag = false;
        this.newTag = false;
        this.recommendTag = false;
        this.likes = 0;
        this.views = 0;
    }
}
