package spring.aws.lambda.run;

import dev.ktech.aws.lambda.NoInputOutputHandler;
import dev.ktech.aws.lambda.NotificationLevel;
import dev.springpayments.core.model.resulttype.ErrorMsg;
import dev.springpayments.core.model.resulttype.Result;
import dev.springpayments.service.SpringPaymentService;

import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class MarkCardsAsClosedHandler extends NoInputOutputHandler {
    public MarkCardsAsClosedHandler() {
        super("SPRING", "Mark Cards As Closed Handler", NotificationLevel.ON_FAIL);
        setProduction(true);
    }

    @Override
    public void handleRequest() {
        SpringPaymentService springPaymentService = new SpringPaymentService();

        Result<List<Long>, ErrorMsg> resultCloseCards = springPaymentService.enforceCloseVirtualCards();
        if (resultCloseCards.isError()) {
            if (resultCloseCards.isError()) {
                System.out.printf("[ERROR] %s%n", resultCloseCards.getError().get().getDescription());
            } else {
                System.out.printf("[SUCCESS] %s%n", resultCloseCards.getSuccess().get());
            }
        }
    }
}
