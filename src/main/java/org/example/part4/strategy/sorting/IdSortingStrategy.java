package org.example.part4.strategy.sorting;

import org.example.part4.user.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IdSortingStrategy implements UserSortingStrategy {

    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparingLong(User::getId))
                .collect(Collectors.toList());
    }

    @Override
    public String getStrategyName() {
        return "Сортировка по ID";
    }
}