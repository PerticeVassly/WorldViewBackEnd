package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.enums.PhotoTheme;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "theme")
    private PhotoTheme theme;

}
