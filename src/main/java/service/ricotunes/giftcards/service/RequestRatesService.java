package service.ricotunes.giftcards.service;


import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.RequestRates;

import java.util.List;

@Service
public interface RequestRatesService {
    RequestRates addRequest(RequestRates requestRates);

    List<RequestRates> getRequest();
}
