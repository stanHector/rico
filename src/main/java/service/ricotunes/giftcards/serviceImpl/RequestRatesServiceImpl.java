package service.ricotunes.giftcards.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ricotunes.giftcards.model.RequestRates;
import service.ricotunes.giftcards.repository.RequestRateRepository;
import service.ricotunes.giftcards.service.RequestRatesService;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RequestRatesServiceImpl  implements RequestRatesService {
    private final RequestRateRepository requestRateRepository;


    @Override
    public RequestRates addRequest(RequestRates requestRates) {
        RequestRates requests = new RequestRates();
        requests.setRequestDate(requestRates.getRequestDate());
        requests.setQuantity(requestRates.getQuantity());
        requests.setAmount(requestRates.getAmount());
        requests.setImageList(requestRates.getImageList());
        requests.setType(requestRates.getType());
        requests.setUserId(requestRates.getUserId());
        requests.setCountry(requestRates.getCountry());
        requests.setComment(requestRates.getComment());
        return requestRateRepository.save(requestRates);
    }

    @Override
    public List<RequestRates> getRequest() {
        return requestRateRepository.findAll();
    }
}
