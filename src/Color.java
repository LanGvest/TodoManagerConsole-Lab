public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[1;31m"),
    YELLOW("\u001B[1;33m"),
    GREEN("\u001B[1;32m"),
    CYAN("\u001B[36m"),
    STRIKETHROUGH("\u001B[9m");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}