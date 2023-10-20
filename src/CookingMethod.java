public enum CookingMethod implements Comparable<CookingMethod>{
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

//    public static void main(String[] args) {
//        CookingMethod roast = CookingMethod.ROAST;
//        CookingMethod fry = CookingMethod.FRY;
//
//        System.out.println();
//        System.out.println("Cooking Method: " + fry.getName() + ", Priority: " + fry.getPriority());
//    }
//    @Override
//    public int compareTo(CookingMethod other) {
//        return this.getPriority() - other.getPriority();
//    }
}