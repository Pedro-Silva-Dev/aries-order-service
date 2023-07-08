package br.com.solipy.ariesrabbitmqpublish.models.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder(setterPrefix = "set")
@Data
public class OrderServiceDto implements Serializable {
    private BigDecimal total;
    private String paymentToken;
}
