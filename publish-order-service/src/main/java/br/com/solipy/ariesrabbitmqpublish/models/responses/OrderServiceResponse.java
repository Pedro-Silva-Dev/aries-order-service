package br.com.solipy.ariesrabbitmqpublish.models.responses;

import lombok.Builder;

@Builder(setterPrefix = "set")
public record OrderServiceResponse(
        Boolean success
) {
}
