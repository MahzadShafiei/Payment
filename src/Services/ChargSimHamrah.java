package Services;

import java.time.LocalDate;
import Contract.Pos;
import Contract.Sms;
import Contract.Charg;
import Enums.Menu;

public class ChargSimHamrah extends ChargSim implements Charg {
    public ChargSimHamrah(Pos pos, Sms sms, String cardNumber, LocalDate withdrawalDate, double amount,  Menu selectedMenu) {
        super(pos, sms, cardNumber, withdrawalDate, amount,   selectedMenu);
    }

    public void getDestinationAccount() {
        super.recieveAccountNumber = "789";
    }
}
