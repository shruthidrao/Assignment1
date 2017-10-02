import java.sql.Date;
import java.util.Calendar;

public class Data {

    private TradeType tradeType;
    private Currency currency;
    private String entity;
    private Date instructionDate;
    private Date settlementDate;
    private int units;
    private float pricePerUnit;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
    	System.out.println("here in sette");
        if(this.settlementDate!=null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.settlementDate);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if(dayOfWeek==1){  //Sunday
                if(!(Currency.AED.equals(this.currency) || Currency.SGP.equals(this.currency))){
                    calendar.add(Calendar.DATE, 1);
                    return (Date)calendar.getTime();
                }
            }

            if(dayOfWeek==7){  //Saturday
                if(!(Currency.AED.equals(this.currency) || Currency.SGP.equals(this.currency))){
                    calendar.add(Calendar.DATE, 2);
                    return (Date)calendar.getTime();
                } else {
                    calendar.add(Calendar.DATE, 1);
                    return (Date)calendar.getTime();
                }
            }

            if(dayOfWeek==6){ // Friday
                if(Currency.AED.equals(this.currency) || Currency.SGP.equals(this.currency)){
                    calendar.add(Calendar.DATE, 2);
                    return (Date)calendar.getTime();
                }
            }
            return settlementDate;
        }
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public String toString() {
        return "Data{" +
                "tradeType=" + tradeType +
                ", currency=" + currency +
                ", entity='" + entity + '\'' +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", units=" + units +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}


