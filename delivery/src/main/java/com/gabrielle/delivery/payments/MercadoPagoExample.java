package com.gabrielle.delivery.payments;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.core.MPRequestOptions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

public class MercadoPagoExample {
    public static void main(String[] args) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken("TEST-512424128876225-031517-2b6ae6222638595d2911fd6e19e532e6-2329054963");

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", "<SOME_UNIQUE_VALUE>");

        MPRequestOptions requestOptions = MPRequestOptions.builder()
            .customHeaders(customHeaders)
            .build();

        PaymentClient client = new PaymentClient();

        List<PaymentItemRequest> items = new ArrayList<>();

        PaymentItemRequest item =
            PaymentItemRequest.builder()
                .id("MLB2907679857")
                .title("Point Mini")
                .description("Point product for card payments via Bluetooth.")
                .pictureUrl(
                    "https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium2x.png")
                .categoryId("electronics")
                .quantity(1)
                .unitPrice(new BigDecimal("58.8"))
                .build();

        items.add(item);

        PaymentCreateRequest createRequest =
            PaymentCreateRequest.builder()
                .additionalInfo(
                    PaymentAdditionalInfoRequest.builder()
                        .items(items)
                        .payer(
                            PaymentAdditionalInfoPayerRequest.builder()
                                .firstName("Test")
                                .lastName("Test")
                                .phone(PhoneRequest.builder().areaCode("11").number("987654321").build())
                                .build())
                        .shipments(
                            PaymentShipmentsRequest.builder()
                                .receiverAddress(
                                    PaymentReceiverAddressRequest.builder()
                                        .zipCode("12312-123")
                                        .stateName("Rio de Janeiro")
                                        .cityName("Buzios")
                                        .streetName("Av das Nacoes Unidas")
                                        .streetNumber("3003")
                                        .build())
                                .build())
                        .build())
                .binaryMode(false)
                .capture(false)
                .description("Payment for product")
                .externalReference("MP0001")
                .installments(1)
                .order(PaymentOrderRequest.builder().type("mercadolibre").build())
                .payer(PaymentPayerRequest.builder()
                    .entityType("individual")
                    .type("customer")
                    .email("test_user_123@testuser.com")
                    .identification(IdentificationRequest.builder()
                        .type("CPF")
                        .number("01234567890")
                        .build())
                    .build())
                .paymentMethodId("master")
                .token("ff8080814c11e237014c1ff593b57b4d")
                .transactionAmount(new BigDecimal("58.8"))
                .build();
        client.create(createRequest, requestOptions);
    }
}
