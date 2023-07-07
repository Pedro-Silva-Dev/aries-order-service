package br.com.solipy.ariesrabbitmqpublish.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
public record OrderPaymentRequest(
        @NotNull @NotEmpty String fullName,
        @NotNull @NotEmpty String workOrder,
        @NotNull @Min(1) BigDecimal total
) {
}
