public enum CookingMethod {
    ROAST("roast", 1),
    FRY("fry", 2);

    private String name;
    private int priority;

    private CookingMethod(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return name;
    }

}