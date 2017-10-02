public enum Currency {
    USD(1), AED(0.22F), SAR(0.5F), SGP(0.5F);

    private float agreedFx;

    Currency(float agreedFx){
        this.agreedFx = agreedFx;
    }

    public static float getAgreedFx(Currency currency){
        return currency.agreedFx;
    }
};