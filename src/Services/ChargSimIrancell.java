package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.Charg;
import Enums.Menu;

public class ChargSimIrancell extends ChargSim implements Charg {
    public ChargSimIrancell(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount, Menu selectedMenu) {
        super(pos, sms, cardNumber, withdrawalDate, amount,  selectedMenu);
    }
    public void getDestinationAccount() {
        super.recieveAccountNumber = "258";
    }
}
