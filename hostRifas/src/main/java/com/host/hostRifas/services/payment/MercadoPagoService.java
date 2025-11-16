package com.host.hostRifas.services.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.host.hostRifas.models.raffle.NumberModel;
import com.host.hostRifas.models.raffle.RaffleModel;
import com.host.hostRifas.models.user.UserModel;
import com.host.hostRifas.repositories.IRaffleRepository;
import com.host.hostRifas.repositories.IUserRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

@Service
public class MercadoPagoService {
    
    private IRaffleRepository iRaffleRepository;
    private IUserRepository iUserRepository;

    public MercadoPagoService(IRaffleRepository iRaffleRepository, IUserRepository iUserRepository){
        this.iRaffleRepository = iRaffleRepository;
        this.iUserRepository = iUserRepository;
    }

    public String payment(int select, Long raffleId, Long userId) throws Exception{

        MercadoPagoConfig.setAccessToken("TEST_ACCESS_TOKEN");

        RaffleModel raffle = this.iRaffleRepository.findById(raffleId).get();
        UserModel user = this.iUserRepository.findById(userId).get();

        List<PreferenceItemRequest> items = new ArrayList();

        for(NumberModel number : raffle.getNumbers()){
            if(number.getNumber() == select){
                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(LocalDateTime.now().toString())
                .title(""+select)
                .categoryId("raffle")
                .quantity(1)
                .currencyId("BRL")
                .unitPrice(new BigDecimal(raffle.getValue()))
                .build();

                items.add(itemRequest);

                PreferenceBackUrlsRequest backUrls =

                PreferenceBackUrlsRequest.builder()
                    .success("http://localhost/success")
                    .pending("http://localhost/pending")
                    .failure("http://localhost/failure")
                    .build();

                PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).backUrls(backUrls).build();

                PreferenceClient client = new PreferenceClient();
                Preference preference = client.create(preferenceRequest);

                return preference.getSandboxInitPoint();
            }
            
        }

        return "";

    }

}
