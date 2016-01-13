package gamepub.beans;

import gamepub.db.entity.Comment;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import gamepub.db.entity.User;
import gamepub.db.service.CommentService;
import gamepub.db.service.NewsService;
import gamepub.db.service.UserService;
import gamepub.dto.NewsDto;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.primefaces.component.inputtextarea.InputTextarea;

/**
 * Created by roman on 03.12.15.
 */
@ManagedBean
@SessionScoped
@RequestScoped
public class NewsBean {

    @ManagedProperty(value = "#{param.id}")
    private int id;

    @EJB
    NewsService newsService;
    @EJB
    CommentService commentService;
    @EJB
    UserService userService;

    List<Comment> comments;

    public News getNews() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("id", id);
        return newsService.getNewsById(id);
    }

    public List<Comment> getComments() {
        comments = commentService.getCommentsByNewsId(id);
        return comments;
    }

    public void addComment() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        UIViewRoot uiViewRoot = context.getViewRoot();
        InputTextarea inputText = null;
        inputText = (InputTextarea) uiViewRoot.
                findComponent("commentAdderForm:commentAdderNewComment");
        String comment = (String) inputText.getValue();
        if (comment == null || comment.isEmpty() || comment.length() >= 501) {
            return;
        }

        Comment comm = new Comment();
        comm.setDate(new java.util.Date());
        comm.setText(comment);
        comm.setUser(userService.getUserById(1));
        comm.setNews(newsService.getNewsById(Integer.valueOf(context.getExternalContext().getSessionMap().get("id").toString())));
        context.getExternalContext().getSessionMap().remove("id");
        commentService.create(comm);

        inputText.setValue("");
        comments = commentService.getCommentsByNewsId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
