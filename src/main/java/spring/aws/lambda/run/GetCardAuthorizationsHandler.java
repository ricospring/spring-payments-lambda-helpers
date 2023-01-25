package spring.aws.lambda.run;

import dev.ktech.aws.lambda.NoInputOutputHandler;
import dev.ktech.aws.lambda.NotificationLevel;
import dev.springpayments.core.model.resulttype.ErrorMsg;
import dev.springpayments.core.model.resulttype.Result;
import dev.springpayments.service.SpringPaymentService;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class GetCardAuthorizationsHandler extends NoInputOutputHandler {
    public GetCardAuthorizationsHandler() {
        super("SPRING", "Get Card Authorizations Handler", NotificationLevel.ON_FAIL);
        setProduction(true);
    }

    @Override
    public void handleRequest() {
        SpringPaymentService springPaymentService = new SpringPaymentService();
        Result<String, ErrorMsg> resultAuth = springPaymentService.getAndApplyCardAuthorizations();
        if (resultAuth.isError()) {
            System.out.printf("[ERROR] %s%n", resultAuth.getError().get().getDescription());
        } else {
            System.out.printf("[SUCCESS] %s%n", resultAuth.getSuccess().get());
        }
    }
}
