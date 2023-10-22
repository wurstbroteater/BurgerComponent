package burger;

public enum Ingredients {
    CHEESE("cheese"),
    VEGAN_CHEESE("vegan_cheese"),
    BEEF("beef"),
    VEGAN_BEEF("vegan_beef"),
    BACON("bacon"),
    VEGAN_BACON("vegan_bacon"),
    EGG("egg"),
    LETTUCE("lettuce"),
    ONION("onion"),
    JALAPENO("jalapeno"),
    TOMATO("tomato"),
    PICKLE("pickle"),
    KETCHUP("ketchup"),
    MUSTARD("mustartd"),
    BBQ("bbq");

    private String name;

    Ingredients(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }
}
