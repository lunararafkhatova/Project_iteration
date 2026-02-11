List<Activity> getByUserId(int userId);
boolean update(Activity activity);
List<Activity> searchByName(String keyword);
List<Activity> getBetweenDates(LocalDate start, LocalDate end);
