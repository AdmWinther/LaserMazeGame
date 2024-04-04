package Classes.Token;

public record TokenID(String value) {
    public static final TokenID EMPTY = new TokenID("");

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TokenID other)) {
            return false;
        }
        return value.equals(other.value);
    }

}
