package service.ricotunes.giftcards.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ricotunes.giftcards.model.RequestRates;
import service.ricotunes.giftcards.repository.RequestRateRepository;
import service.ricotunes.giftcards.service.RequestRatesService;

import java.time.LocalDate;

@RequiredArgsConstructor
@Transactional
@Service
public class RequestRatesServiceImpl  implements RequestRatesService {
    private final RequestRateRepository requestRateRepository;


//    @Override
//    public RequestRates addRequest(RequestRates requestRates) {
//        RequestRates requests = new RequestRates();
//
//        return requestRateRepository.save(requestRates);
//    }
}
