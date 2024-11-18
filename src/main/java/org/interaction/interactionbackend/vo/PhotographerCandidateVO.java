package org.interaction.interactionbackend.vo;

import lombok.Getter;
import lombok.Setter;
import org.interaction.interactionbackend.po.PhotographerCandidate;
import org.interaction.interactionbackend.po.User;

/**
 * email: email of the candidate <br>
 * headImg: head image of the candidate <br>
 * uname: username of the candidate <br>
 * contact: contact of the candidate <br>
 * description: description of the candidate <br>
 * photo: photo of the candidate <br>
 * votes: votes of the candidate <br>
 */
@Getter
@Setter
public class PhotographerCandidateVO {

    String email;
    String headImg;
    String uname;
    String contact;
    String description;
    String photo;
    Integer votes;

    public PhotographerCandidateVO(PhotographerCandidate photographerCandidate, User user) {
        this.email = user.getEmail();
        this.headImg = user.getHeadImg();
        this.uname = user.getUname();
        this.contact = photographerCandidate.getContact();
        this.description = photographerCandidate.getDescription();
        this.photo = photographerCandidate.getPhoto();
        this.votes = photographerCandidate.getVotes();
    }
}
