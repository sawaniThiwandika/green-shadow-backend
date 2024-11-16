package lk.ijse.greenshadowbackend.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCropCode(){
        return "CR-"+ UUID.randomUUID();
    }
    public static String generateFieldId(){
        return "F-"+UUID.randomUUID();
    }
    public static String generateEquipmentId(){
        return "EQ-"+UUID.randomUUID();
    }
    public static String generateVehicleCode(){
        return "V-"+UUID.randomUUID();
    }
    public static String generateLogId(){
        return "L-"+UUID.randomUUID();
    }
    public static String generateStaffId(){
        return "S-"+UUID.randomUUID();
    }
    public static String profilePicToBase64(byte[] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
