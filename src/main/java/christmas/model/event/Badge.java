package christmas.model.event;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTHING("없음", 0);

    private final String name;
    private final int minBenefitAmount;

    Badge(String name, int minBenefitAmount) {
        this.name = name;
        this.minBenefitAmount = minBenefitAmount;
    }

    public static Badge getBadgeByBenefitAmount(int benefitAmount) {
        if (benefitAmount >= SANTA.minBenefitAmount) {
            return SANTA;
        }
        if (benefitAmount >= TREE.minBenefitAmount) {
            return TREE;
        }
        if (benefitAmount >= STAR.minBenefitAmount) {
            return STAR;
        }
        return NOTHING;
    }

    @Override
    public String toString() {
        return name;
    }
}
