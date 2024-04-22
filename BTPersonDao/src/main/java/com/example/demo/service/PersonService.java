package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;

import java.util.List;
import java.util.Map;

public interface PersonService {
    void printListPeople(List<Person> persons);

    List<Person> getAll();

    PageResponse<Person> getAllPeople(int page, int pageSize);

    List<Person> sortPeopleByFullName();
    List<Person> sortPeopleByFullNameReversed();

    List<String> getSortedJobs();

    List<String> getSortedCities();
    List<String> femaleNames();

    Person highestEarner();

    List<Person> bornAfter1990();

    double averageSalary();

    double averageAge();

    List<Person> nameContains(String keyword);

    List<Person> sortedByAgeForMale();

    Person longestName();

    List<Person> aboveAverageSalary();
    Map<String, List<Person>> groupPeopleByCity();
}
