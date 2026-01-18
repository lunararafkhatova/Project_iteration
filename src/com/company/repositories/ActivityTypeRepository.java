package com.company.repositories;

import com.company.models.ActivityType;
import java.util.ArrayList;
import java.util.List;

public class ActivityTypeRepository {
    private final List<ActivityType> types = new ArrayList<>();

    public ActivityTypeRepository() {
        types.add(new ActivityType(1, "RUNNING"));
        types.add(new ActivityType(2, "SWIMMING"));
        types.add(new ActivityType(3, "YOGA"));
        types.add(new ActivityType(4, "CYCLING"));
        types.add(new ActivityType(5, "OTHER"));
    }

    public List<ActivityType> getAll() {
        return types;
    }

    public ActivityType getById(int id) {
        return types.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void add(ActivityType type) {

    }
}