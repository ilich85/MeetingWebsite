package com.ilich.service;

import com.ilich.model.SearchRequest;
import com.ilich.repository.SearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private SearchRepository searchRepository
            = AppContext.getContext().getBean(SearchRepository.class);

    @Override
    public List<Object[]> getUsersByAllFields(SearchRequest sr) {
        return searchRepository.usersByAllFields(
                sr.getCity(), sr.getGender(), sr.getMinAge(), sr.getMaxAge());
    }

    @Override
    public List<Object[]> getUsersByCity(String city) {
        return searchRepository.usersByCity(city);
    }

    @Override
    public List<Object[]> getUsersByGender(String gender) {
        return searchRepository.usersByGender(gender);
    }

    @Override
    public List<Object[]> getUsersByMinAge(int minAge) {
        return searchRepository.usersByMinAge(minAge);
    }

    @Override
    public List<Object[]> getUsersByMinAgeAndCity(int minAge, String city) {
        return searchRepository.usersByMinAgeAndCity(minAge, city);
    }

    @Override
    public List<Object[]> getUsersByMinAgeAndGender(int minAge, String gender) {
        return searchRepository.usersByMinAgeAndGender(minAge, gender);
    }

    @Override
    public List<Object[]> getUsersByMinAgeAndGenderAndCity(SearchRequest sr) {
        return searchRepository.usersByMinAgeAndGenderAndCity(sr.getMinAge(), sr.getGender(), sr.getCity());
    }

    @Override
    public List<Object[]> getUsersByMaxAge(int maxAge) {
        return searchRepository.usersByMaxAge(maxAge);
    }

    @Override
    public List<Object[]> getUsersByMaxAgeAndCity(int maxAge, String city) {
        return searchRepository.usersByMaxAgeAndCity(maxAge, city);
    }

    @Override
    public List<Object[]> getUsersByMaxAgeAndGender(int maxAge, String gender) {
        return searchRepository.usersByMaxAgeAndGender(maxAge, gender);
    }

    @Override
    public List<Object[]> getUsersByMaxAgeAndGenderAndCity(SearchRequest sr) {
        return searchRepository.usersByMaxAgeAndGenderAndCity(sr.getMaxAge(), sr.getGender(), sr.getCity());
    }

    @Override
    public List<Object[]> getUsersByAge(int minAge, int MaxAge) {
        return searchRepository.usersByAge(minAge, MaxAge);
    }

    @Override
    public List<Object[]> getUsersByCityAndGender(String city, String gender) {
        return searchRepository.usersByCityAndGender(city, gender);
    }

    @Override
    public List<Object[]> getUsersByAgeAndGender(SearchRequest sr) {
        return searchRepository.usersByAgeAndGender(sr.getMinAge(), sr.getMaxAge(), sr.getGender());
    }

    @Override
    public List<Object[]> getUsersByAgeAndCity(SearchRequest sr) {
        return searchRepository.usersByAgeAndCity(sr.getMinAge(), sr.getMaxAge(), sr.getCity());
    }
}