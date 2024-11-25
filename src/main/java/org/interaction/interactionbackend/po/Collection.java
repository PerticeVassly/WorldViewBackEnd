package org.interaction.interactionbackend.po;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "collectingId", nullable = false)
    private Integer collectingId;

    @Column(name = "collectedId", nullable = false)
    private Integer collectedId;

    public Collection(Integer collectingId, Integer collectedId) {
        this.collectingId = collectingId;
        this.collectedId = collectedId;
    }

    public Collection() {}
}
