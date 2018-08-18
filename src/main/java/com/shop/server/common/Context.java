package com.shop.server.common;

import com.shop.server.model.ManageCompany;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Date;

public interface Context {
    Logger logger = LoggerFactory.getLogger(Context.class);
    String WX_OPENED = "https://api.weixin.qq.com/sns/jscode2session";
    String WX_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    String WX_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    static String getOrderId(ManageCompany manageCompany){
        String companyName = manageCompany.getCompanyName();
        String order = DateUtils.formatDate(new Date(),"yyyyMMddhhmmss");
        return companyName + "-" + order;
    }
    static String getSystem(){
        String os = System.getProperty("os.name");
        logger.info("system ============> {}",os);
        return os.toLowerCase();
    }
    static String uploadAddr(){
        if(getSystem().startsWith("win")){
            return "D:/javaSoft/nginx-1.12.2/nginx-1.12.2/html/upload/";
        }else{
            return "/usr/local/nginx/html/upload/";
        }
    }
    static File getHttpsFile(String classpathName) throws IOException {
        if(getSystem().startsWith("win")){
            File keystore = new ClassPathResource(classpathName).getFile();
            return keystore;
        }else{
            InputStream keystore = new ClassPathResource(classpathName).getInputStream();
            byte[] bytes = new byte[1024];
            File file = new File("/usr/java/jkss");
            if(!file.exists()){
                file.mkdirs();
            }
            if(!file.canRead()){
                file.setReadable(true);
            }
            if(file.canWrite()){
                file.setWritable(true);
            }
            File jks = new File(file,classpathName);
            if(!jks.exists()){
                jks.createNewFile();
            }
            if(!jks.canRead()){
                jks.setReadable(true);
            }
            if(jks.canWrite()){
                jks.setWritable(true);
            }
            OutputStream outputStream = new FileOutputStream(jks);
            while(keystore.read(bytes) != -1){
                outputStream.write(bytes);
            }
            jks.setWritable(false);
            return jks;
        }
    }
//    Long DEFAULT_COMPANY_ID = 1L;
    String FILE_HOST = "http://127.0.0.1/upload/";
}
