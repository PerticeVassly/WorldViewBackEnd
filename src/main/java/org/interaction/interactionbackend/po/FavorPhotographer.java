package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FavorPhotographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "favoringId", nullable = false)
    private Integer favoringId;

    @Column(name = "favoredId", nullable = false)
    private Integer favoredId;

    public FavorPhotographer(Integer favoringId, Integer favoredId) {
        this.favoringId = favoringId;
        this.favoredId = favoredId;
    }

    public FavorPhotographer() {}
}
