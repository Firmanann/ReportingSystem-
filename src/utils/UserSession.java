package utils;

public class UserSession {
    
    private static String user;
    
    public static String getUser(){
        return user;
    }
    
    public static void setUser(String nim){
        user = nim;
    }
    
}
