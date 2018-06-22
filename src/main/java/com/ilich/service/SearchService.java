package com.ilich.service;

import com.ilich.model.SearchRequest;

import java.util.List;

public interface SearchService {

    List<Object[]> getUsersByCity(String city);

    List<Object[]> getUsersByGender(String gender);

    List<Object[]> getUsersByMinAge(int minAge);

    List<Object[]> getUsersByMaxAge(int maxAge);

    List<Object[]> getUsersByAge(int minAge, int MaxAge);

    List<Object[]> getUsersByMinAgeAndCity(int minAge, String city);

    List<Object[]> getUsersByMinAgeAndGender(int minAge, String gender);

    List<Object[]> getUsersByMaxAgeAndCity(int maxAge, String city);

    List<Object[]> getUsersByMaxAgeAndGender(int maxAge, String gender);

    List<Object[]> getUsersByCityAndGender(String city, String gender);

    List<Object[]> getUsersByAgeAndCity(SearchRequest searchRequest);

    List<Object[]> getUsersByAgeAndGender(SearchRequest searchRequest);

    List<Object[]> getUsersByMinAgeAndGenderAndCity(SearchRequest searchRequest);

    List<Object[]> getUsersByMaxAgeAndGenderAndCity(SearchRequest searchRequest);

    List<Object[]> getUsersByAllFields(SearchRequest searchRequest);
}