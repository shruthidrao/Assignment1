import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *  A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
 •	A trade can only be settled on a working day.
 •	If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day.
 •	USD amount of a trade = Price per unit * Units * Agreed Fx
 •	Requirements
 •	Create a report that shows
 •	Amount in USD settled incoming everyday
 •	Amount in USD settled outgoing everyday
 •	Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing
 */
public class AssignmentOne {

    public static void main(String[] args){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Data[] data = getData();
        Map<String, Float> incoming = new HashMap<String, Float>();
        Map<String, Float> outgoing = new HashMap<String, Float>();

        for(Data input: data){
            if(input!=null){
                if(TradeType.B.equals(input.getTradeType())){
                    if(input!=null && incoming.containsKey(formatter.format(input.getSettlementDate()))){
                        incoming.put(formatter.format(input.getSettlementDate()), incoming.get(formatter.format(input.getSettlementDate())) +
                                Currency.getAgreedFx(input.getCurrency()) * input.getUnits() * input.getPricePerUnit());
                    } else {
                        incoming.put(formatter.format(input.getSettlementDate()), Currency.getAgreedFx(input.getCurrency()) * input.getUnits() * input.getPricePerUnit());
                    }
                } else {
                    if(input!=null && outgoing.containsKey(formatter.format(input.getSettlementDate()))){
                        outgoing.put(formatter.format(input.getSettlementDate()), outgoing.get(formatter.format(input.getSettlementDate())) +
                                Currency.getAgreedFx(input.getCurrency()) * input.getUnits() * input.getPricePerUnit());
                    } else {
                        outgoing.put(formatter.format(input.getSettlementDate()), Currency.getAgreedFx(input.getCurrency()) * input.getUnits() * input.getPricePerUnit());
                    }
                }
            }
        }

        // Report for incoming


        // Report for outgoing
        System.out.println(incoming);
        System.out.println(outgoing);

    }

    private static Data[] getData(){

        Data[] data = new Data[5];
        Data tempData;

        //----------Sample Data 1---------------//
        tempData = new Data();
        tempData.setEntity("foo");
        tempData.setTradeType(TradeType.B);
        tempData.setCurrency(Currency.USD);
        tempData.setInstructionDate(Date.valueOf("2016-1-1"));
        tempData.setSettlementDate(Date.valueOf("2016-1-7"));
        tempData.setUnits(100);
        tempData.setPricePerUnit(20F);
        data[0] = tempData;

        //----------Sample Data 2---------------//
        tempData = new Data();
        tempData.setEntity("foo");
        tempData.setTradeType(TradeType.B);
        tempData.setCurrency(Currency.USD);
        tempData.setInstructionDate(Date.valueOf("2016-1-5"));
        tempData.setSettlementDate(Date.valueOf("2016-1-7"));
        tempData.setUnits(100);
        tempData.setPricePerUnit(20F);
        data[1] = tempData;

        //----------Sample Data 3---------------//
        tempData = new Data();
        tempData.setEntity("bar");
        tempData.setTradeType(TradeType.S);
        tempData.setCurrency(Currency.USD);
        tempData.setInstructionDate(Date.valueOf("2016-1-1"));
        tempData.setSettlementDate(Date.valueOf("2016-1-7"));
        tempData.setUnits(200);
        tempData.setPricePerUnit(50F);
        data[2] = tempData;

        //----------Sample Data 4---------------//
        tempData = new Data();
        tempData.setEntity("bar");
        tempData.setTradeType(TradeType.S);
        tempData.setCurrency(Currency.USD);
        tempData.setInstructionDate(Date.valueOf("2016-1-5"));
        tempData.setSettlementDate(Date.valueOf("2016-1-7"));
        tempData.setUnits(200);
        tempData.setPricePerUnit(50F);
        data[3] = tempData;

        return data;
    }

}
