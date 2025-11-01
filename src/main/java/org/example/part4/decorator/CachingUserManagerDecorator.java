package org.example.part4.decorator;

import org.example.part4.user.User;
import org.example.part4.user.UserManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingUserManagerDecorator extends UserManagerDecorator {
    private final Map<String, String> userInfoCache = new HashMap<>();
    private List<User> usersCache;
    private long cacheTimestamp;
    private static final long CACHE_DURATION = 30000;

    public CachingUserManagerDecorator(UserManager userManager) {
        super(userManager);
    }

    @Override
    public List<User> getUsers() {
        if (usersCache != null && (System.currentTimeMillis() - cacheTimestamp) < CACHE_DURATION) {
            System.out.println("CACHING: Возвращаем список пользователей из кэша");
            return usersCache;
        }

        System.out.println("CACHING: Кэш устарел, запрашиваем данные...");
        usersCache = super.getUsers();
        cacheTimestamp = System.currentTimeMillis();
        return usersCache;
    }

    @Override
    public String getUserInfo(String username) {
        if (userInfoCache.containsKey(username)) {
            System.out.println("CACHING: Возвращаем информацию из кэша для '" + username + "'");
            return userInfoCache.get(username);
        }

        System.out.println("CACHING: Информация для '" + username + "' не в кэше, запрашиваем...");
        String userInfo = super.getUserInfo(username);
        userInfoCache.put(username, userInfo);
        return userInfo;
    }

    @Override
    public void addUser(String username, String password, String userInfo) {
        System.out.println("CACHING: Инвалидация кэша из-за добавления пользователя");
        invalidateCache();
        super.addUser(username, password, userInfo);
    }

    @Override
    public boolean deleteUser(String username) {
        System.out.println("CACHING: Инвалидация кэша из-за удаления пользователя");
        invalidateCache();
        userInfoCache.remove(username);
        return super.deleteUser(username);
    }

    private void invalidateCache() {
        usersCache = null;
        userInfoCache.clear();
        System.out.println("CACHING: Кэш полностью очищен");
    }
}