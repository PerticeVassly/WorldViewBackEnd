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
    private String url;
    private String description;
    private PhotoTheme theme;

    public PhotoVO(Photo photo, User user) {
       this.userEmail = user.getEmail();
       this.url = photo.getUrl();
       this.description = photo.getDescription();
       this.theme = photo.getTheme();
    }
}
