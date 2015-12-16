/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.dto;

import gamepub.db.entity.Comment;
import java.awt.Image;
import java.util.List;

/**
 *
 * @author Иван
 */
public class GameDto {
    String title;
    String text;
    Image image;
    List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setImage(Image image){
        this.image=image;
    }
    public Image getImage(){
        return image;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
