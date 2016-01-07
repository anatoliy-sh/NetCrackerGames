/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.beans;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import gamepub.db.entity.User;
import gamepub.db.entity.UserScreenshot;
import gamepub.db.service.UserScreenshotService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fitok
 */
@ManagedBean(name = "GalleryBean")
@SessionScoped
public class GalleryBean {
    
   @EJB
   UserScreenshotService userScreenshotService;
private User user;   
private List<String> images;
private UploadedFile file;
private Map uploadResult;
private int i;

public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws IOException {
        if(getFile() != null) {
      
            Map options = ObjectUtils.asMap("cloud_name", "dtx5nrsak",
  "api_key", "152549788856848",
  "api_secret", "yJVWUKhjo_3tr9wEnOndydIEGtY");
            Cloudinary cloudinary = new Cloudinary(options);
            uploadResult = cloudinary.uploader().upload(stream2file(getFile().getInputstream()), options);
            UserScreenshot uScreen = new UserScreenshot();
            uScreen.setLink((String)uploadResult.get("url"));
            System.out.println("ЮРЛА ЮРЛА ЮРЛА "+(String)uploadResult.get("url"));
            Date curDate = new Date();            
            uScreen.setDate(curDate);   
            uScreen.setUser(user);
            uScreen.setId(i++);           
            userScreenshotService.create(uScreen);
            FacesMessage message = new FacesMessage("Succesful", getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    } 
 
@PostConstruct
    public void init(){   
        user = new User();
        user.setId(1);
        i = userScreenshotService.findAll().size();  
        if(i!=0){
            images = new ArrayList<String>();
            int j=0;
            while(userScreenshotService.find(j)!=null){
            images.add(userScreenshotService.find(j).getLink());j++;
            }
        }
    }
    
    /**
     * @return the images
     */
    public List<String> getImages() {
        return images;
    }
    
         public File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile("stream2file", ".tmp");
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
            IOUtils.copy(in, out);
        
        return tempFile;
    }
    
    
}
