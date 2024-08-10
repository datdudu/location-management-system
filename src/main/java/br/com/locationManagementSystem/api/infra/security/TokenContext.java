package br.com.locationManagementSystem.api.infra.security;

public class TokenContext {
    private static final ThreadLocal<String> currentAccessToken = new ThreadLocal<>();

    private static final ThreadLocal<String> currentRefreshToken = new ThreadLocal<>();


    public static void setCurrentRefreshToken(String token) {
        currentRefreshToken.set(token);
    }

    public static String getRefreshToken() {
        return currentRefreshToken.get();
    }

    public static void setToken(String token) {
        currentAccessToken.set(token);
    }

    public static String getToken() {
        return currentAccessToken.get();
    }

    public static void clear() {
        currentAccessToken.remove();
    }
}
