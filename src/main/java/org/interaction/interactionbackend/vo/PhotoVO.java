package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.interaction.interactionbackend.enums.PhotoTheme;
import org.interaction.interactionbackend.po.Photo;
import org.interaction.interactionbackend.po.User;

@Getter
@Setter
@NoArgsConstructor
public class PhotoVO {

    private String userEmail;
    private String uname;
    private String url;
    private String title;
    private String description;
    private PhotoTheme theme;
    private Integer likes;
    private Integer views;
    private Boolean rankingTag;
    private Boolean newTag;
    private Boolean recommendTag;


    public PhotoVO(Photo photo, User user) {
        this.userEmail = user.getEmail();
        this.url = photo.getUrl();
        this.description = photo.getDescription();
        this.title = photo.getTitle();
        this.uname = user.getUname();
        this.theme = photo.getTheme();
        this.likes = photo.getLikes();
        this.views = photo.getViews();
        this.rankingTag = photo.getRankingTag();
        this.newTag = photo.getNewTag();
        this.recommendTag = photo.getRecommendTag();
    }
}
