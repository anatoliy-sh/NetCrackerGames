package gamepub.beans;

import gamepub.db.entity.Comment;
import gamepub.db.entity.Game;
import gamepub.db.entity.News;
import gamepub.db.entity.User;
import gamepub.dto.NewsDto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 03.12.15.
 */
@ManagedBean
@SessionScoped
@RequestScoped
public class NewsBean {
    @ManagedProperty(value = "#{param.id}")
    private int id;

    public NewsDto getNews()
    {
        NewsDto n = new NewsDto();
        //
        News news = new News();
        news.setId(id);
        news.setLink("qqqq");
        news.setDate(new Date());
        news.setName("News");
        Game game = new Game();
        game.setName("Game 1");
        news.setGame(game);
        //
        n.setDate(news.getDate());
        n.setTitle(news.getName()+" "+news.getId());
        n.setDate(news.getDate());
        n.setText("Торт:\n" +
                "Корж: 4 яйца, стакан сахара, стакан муки, 1/4 чайной ложки соды, гашеной уксусом\n" +
                "Пропитка коржей -  обычная кружка кофе\n" +
                "Крем: банка сгущенки и 250гр масла. \n" +
                "Шоколадка.\n" +
                "Приготовление:\n" +
                "Взбиваешь миксером яйца, после добавляешь по очереди сахар, муку и соду с уксусом. Пек в электрической форме около 35-40 минут, в духовке, скорее всего, будет быстрее. Выпекаешь сколько нужно коржей(я сделал два, но они вышли большими, поэтому разрезал их пополам). Готовишь крем: взбиваешь миксиром до однородной массы размягченное масло и сгущенку. Как только это готово, можно приступать к \"конструированию\" торта: каждый корж протыкаешь вилкой, пропитываешь кофе/чаем (чем захочешь), сверху обмазываешь кремом. Перед тем, как обмазывать бока торта, придаешь ему нужную форму (у меня он вышел немножко кривым, ну да ладно). Оставляешь это все на 45-60 минут, после посыпаешь тертой шоколадкой (или можешь сварить глазурь и полить его)\n" +
                "\n" +
                "Пирог с персиком:\n" +
                "Тесто:\n" +
                "250-300гр муки\n" +
                "3 желтка\n" +
                "100гр сахара\n" +
                "3 столовые ложки сметаны\n" +
                "2 чайные ложки разрыхлителя\n" +
                "120гр маргарина\n" +
                "Начинка: \n" +
                "500-600гр консервированных персиков (или абрикосов)\n" +
                "3 яйца\n" +
                "200мл сметаны\n" +
                "30-50гр сахара\n" +
                "Приготовление:\n" +
                "В чашке растираешь мягкий маргарин с сахаром и яичными желтками, затем добавляешь сметану и тщательно перемешиваешь. Выкладываешь тесто в форму, всю поверхность протыкаешь вилкой, включая бортики. После выкладываешь половинки персиков (абрикосов). В блендере взбиваешь яйца, сахар и сметану. После все это вылеваешь в пирог. \n" +
                "Ставишь форму с пирогов на противень и в духовку на 35-40 минут при температуре 180-200 градусов. Заливка будет дрожать - это нормально. После достаешь пирог из духовки и ждешь, пока застынен заливка. Пирог готов. ");
        Comment comment = new Comment();
        User user = new User();
        user.setLogin("User1");
        comment.setText("bababababababbaab");
        comment.setDate(new Date());
        comment.setUser(user);
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment);

        n.setComments(comments);
        return n;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
