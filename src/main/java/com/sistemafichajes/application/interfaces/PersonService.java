package com.sistemafichajes.application.interfaces;

import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto getPersonById(String id);
    void deletePersonById(String id);
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);
    PersonOutputDto updatePerson(PersonInputDto person);
}
