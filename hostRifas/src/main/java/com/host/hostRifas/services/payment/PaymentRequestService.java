package com.host.hostRifas.services.payment;

import org.springframework.stereotype.Service;

import com.host.hostRifas.helpers.payment.PaymentStatus;
import com.host.hostRifas.helpers.raffle.NumberStatus;
import com.host.hostRifas.models.payment.PaymentModel;
import com.host.hostRifas.models.raffle.NumberModel;
import com.host.hostRifas.models.raffle.RaffleModel;
import com.host.hostRifas.models.raffle.WinnerModel;
import com.host.hostRifas.models.user.UserModel;
import com.host.hostRifas.repositories.INumberRepository;
import com.host.hostRifas.repositories.IPaymentRepository;
import com.host.hostRifas.repositories.IRaffleRepository;
import com.host.hostRifas.repositories.IUserRepository;
import com.host.hostRifas.repositories.IWinnerRepository;

@Service
public class PaymentRequestService {
    
    private IRaffleRepository iRaffleRepository;
    private IWinnerRepository iWinnerRepository;
    private IUserRepository iUserRepository;
    private IPaymentRepository iPaymentRepository;

    public PaymentRequestService(IRaffleRepository iRaffleRepository, IUserRepository iUserRepository, IWinnerRepository iWinnerRepository){
        this.iRaffleRepository = iRaffleRepository;
        this.iUserRepository = iUserRepository;
        this.iWinnerRepository = iWinnerRepository;
    }


    public boolean requestPayment(String email, Long raffleId){

        UserModel user = this.iUserRepository.findByEmail(email);
        RaffleModel raffle = this.iRaffleRepository.findById(raffleId).get();

        int qtdSelled = 0;

        for(NumberModel number : raffle.getNumbers()){

            if(number.getStatus() == NumberStatus.purchased){
                qtdSelled += 1;
            }

        }

        WinnerModel winner = this.iWinnerRepository.findByUser(user);

        if(winner.getConfirmationImage() == null){
            return false;
        }

        double paymentValue = qtdSelled * raffle.getValue() - (qtdSelled * raffle.getValue()*0.1);

        PaymentModel payment = new PaymentModel();
        payment.setPaymentValue(paymentValue);
        payment.setRaffle(raffle);
        payment.setStatus(PaymentStatus.waiting);

        return true;

    }


}
