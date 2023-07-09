package br.com.solipy.ariesrabbitmqconsumer.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderServiceDto implements Serializable {
    private BigDecimal total;
    private String paymentToken;
}