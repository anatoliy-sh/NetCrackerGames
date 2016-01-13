package gamepub.beans;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import gamepub.db.entity.City;
import gamepub.db.entity.UserScreenshot;
import gamepub.db.service.CityService;
import gamepub.db.service.NewsService;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ¿Ì‡ÚÓÎËÈ on 13.01.2016.
 */
@ManagedBean
@SessionScoped
public class RegistrBean {

    private UploadedFile file;
    private Map uploadResult;

    public UploadedFile getFile() {
        return file;
    }

    @EJB
    CityService cityService;

    public List<City> getCities(){
        return cityService.findAll();
    }

    /* void upload() throws IOException {
        if(getFile() != null) {

            Map options = ObjectUtils.asMap("cloud_name", "dtx5nrsak",
                    "api_key", "152549788856848",
                    "api_secret", "yJVWUKhjo_3tr9wEnOndydIEGtY");
            Cloudinary cloudinary = new Cloudinary(options);
            uploadResult = cloudinary.uploader().upload(stream2file(getFile().getInputstream()), options);
            UserScreenshot uScreen = new UserScreenshot();
            uScreen.setLink((String)uploadResult.get("url"));
            System.out.println("ﬁ–À¿ ﬁ–À¿ ﬁ–À¿ "+(String)uploadResult.get("url"));
            Date curDate = new Date();
            uScreen.setDate(curDate);
            uScreen.setUser(user);
            uScreen.setId(i++);
            userScreenshotService.create(uScreen);
            FacesMessage message = new FacesMessage("Succesful", getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }*/


}
