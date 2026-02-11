// Get activities by user
public List<Activity> getActivitiesByUser(int userId) {
    return repository.getByUserId(userId);
}

// Update activity
public String updateActivity(int id, String name, int duration, String dateStr) {

    if (name == null || name.isBlank()) {
        return "Validation error: Name is required.";
    }

    if (duration <= 0) {
        return "Validation error: Duration must be greater than zero.";
    }

    LocalDate date;

    try {
        date = LocalDate.parse(dateStr);
    } catch (Exception e) {
        return "Invalid date format. Use YYYY-MM-DD.";
    }

    Activity activity = new Activity();
    activity.setId(id);
    activity.setName(name);
    activity.setDuration(duration);
    activity.setActivityDate(date);

    return repository.update(activity)
            ? "Activity updated successfully!"
            : "Activity not found.";
}

// Search by name
public List<Activity> searchActivities(String keyword) {

    if (keyword == null || keyword.isBlank()) {
        return List.of();
    }

    return repository.searchByName(keyword);
}


public int getTotalMinutesByUser(int userId) {

    return repository.getByUserId(userId)
            .stream()
            .mapToInt(Activity::getDuration)
            .sum();
}

public List<Activity> getActivitiesBetween(String from, String to) {

    try {
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);

        return repository.getBetweenDates(start, end);

    } catch (Exception e) {
        return List.of();
    }
}
