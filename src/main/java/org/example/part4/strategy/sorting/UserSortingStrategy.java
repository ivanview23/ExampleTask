package org.example.part4.strategy.sorting;
import org.example.part4.user.User;

import java.util.List;

public interface UserSortingStrategy {
    List<User> sort(List<User> users);
    String getStrategyName();
}