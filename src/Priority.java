public enum Priority {
    HIGH(Color.RED + "■ High    " + Color.RESET),
    STANDARD(Color.YELLOW + "■ Standard" + Color.RESET),
    LOW(Color.GREEN + "■ Low     " + Color.RESET);

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}