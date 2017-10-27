package home.test.Utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class MongoHelper {

    public static Long generateId(){
        Long id = Long.valueOf(UUID.randomUUID().getMostSignificantBits());
        return id > 0 ? id : Math.abs(id);
    }

    public static String generateCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
        return simpleDateFormat.format(new Date());
    }

    public static String parseType(String fileName){
        String [] parseFileName = fileName.split(Pattern.quote("."));
        if (parseFileName.length > 0){
           if (parseFileName[parseFileName.length - 1].toLowerCase().equals("pdf")){
               return "pdf.png";
           }
           else if (parseFileName[parseFileName.length - 1].toLowerCase().equals("doc") ||
            parseFileName[parseFileName.length - 1].toLowerCase().equals("docx")){
               return "word.png";
           }
           else if (parseFileName[parseFileName.length - 1].toLowerCase().equals("xls") ||
                   parseFileName[parseFileName.length - 1].toLowerCase().equals("xlsx")){
               return "excel.png";
           }
           else if (parseFileName[parseFileName.length - 1].toLowerCase().equals("txt") ||
            (parseFileName[parseFileName.length - 1].toLowerCase().equals("xml"))){
               return "txt.png";
           }
           else if (parseFileName[parseFileName.length - 1].toLowerCase().equals("ppt")){
               return "ppt.png";
           }
           else if (parseFileName[parseFileName.length - 1].toLowerCase().equals("jpg") ||
                    (parseFileName[parseFileName.length - 1].toLowerCase().equals("jpeg"))){
                return "images.png";
            }
           else if (parseFileName[parseFileName.length - 1].toLowerCase().equals("mp3") ||
                   (parseFileName[parseFileName.length - 1].toLowerCase().equals("flac"))){
               return "play.png";
           }
        }
        return "txt.png";
    }

    public static String cutFileName(String name){
        return name.length() <= 40 ? name : name.substring(0, 37) + "...";
    }

    public static String defineUserRole(int userRole){
        return userRole == 17 ? "Admin" : "User";
    }
}
