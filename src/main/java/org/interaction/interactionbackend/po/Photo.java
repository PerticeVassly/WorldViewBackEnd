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

}
