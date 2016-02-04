/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fitok
 */
public class cloudUpload {
    private Map uploadResult;
    private final Map options = ObjectUtils.asMap(
  "cloud_name", "dtx5nrsak",
  "api_key", "152549788856848",
  "api_secret", "yJVWUKhjo_3tr9wEnOndydIEGtY");
    private Cloudinary cloudinary = new Cloudinary(options);   
    public cloudUpload(UploadedFile f) throws IOException{
        uploadResult = cloudinary.uploader().upload(stream2file(f.getInputstream()), options);
    }

    public Map getUploadResult(){    
        return uploadResult;
    }
    
       public File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile("stream2file", ".tmp");
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
            IOUtils.copy(in, out);      
        return tempFile;
    }
            
}
