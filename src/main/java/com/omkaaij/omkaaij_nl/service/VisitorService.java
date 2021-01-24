package com.omkaaij.omkaaij_nl.service;


import com.omkaaij.omkaaij_nl.data.dao.PersonDAO;
import com.omkaaij.omkaaij_nl.data.dao.VisitorDAO;
import com.omkaaij.omkaaij_nl.data.dto.DTO;
import com.omkaaij.omkaaij_nl.model.Person;
import com.omkaaij.omkaaij_nl.model.Visitor;
import com.omkaaij.omkaaij_nl.util.DtoMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j(topic = "VisitorService")
public class VisitorService {

    private final VisitorDAO visitorDAO;
    private final PersonDAO personDAO;

    @Autowired
    public VisitorService (VisitorDAO visitorDAO, PersonDAO personDAO) {
        this.visitorDAO = visitorDAO;
        this.personDAO = personDAO;
    }

    @Transactional
    public <T extends Visitor> void registerVisitor(DTO<T> dto, Class<T> visitorClass) {
        T visitor = DtoMapperUtil.mapDtoToEntity(dto, visitorClass);
        saveVisitor(visitor);
    }

    private <T extends Visitor> void saveVisitor(T entity) {
        try {
            visitorDAO.create(entity);
            if (entity instanceof Person) {
                personDAO.create((Person) entity);
        }
        }catch (DataAccessException e) {
            log.warn("Failed to save visitor [{} - {}]", e.getClass().getSimpleName(), e.getMessage());
        }
    }

    private <T extends Person> void savePerson(T entity) {
        try {
            personDAO.create(entity);
        } catch (DataAccessException e) {
            log.warn("Failed to save person [{} - {}]", e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public Optional<Visitor> getVisitorByUserName (String userName) {
        try {
            return visitorDAO.read(userName);
        } catch (DataAccessException e) {
            log.warn("Visitor not found [{} - {}]", e.getClass().getSimpleName(), e.getMessage());
            return Optional.empty();
        }
    }

    public String printNameVisitor (long visitorID) {
        try {
            Optional<Visitor> optionalVisitor = getVisitorById(visitorID);
            if (optionalVisitor.isPresent()) {
                Person person = (Person) optionalVisitor.get();
                if (person.getPreposition() != null) {
                    return String.format("%s, %s, %s", person.getInitials(), person.getPreposition(), person.getSurName());
                }
                return String.format("%s %s", person.getInitials(), person.getSurName());
                }
            } catch (Exception e) {
                log.error("Er ging iets mis bij het printen van de naam id (" + visitorID + ").");
        }
        return null;
    }

    public Optional<Visitor> getVisitorById (long visitorID) {
        try {
            return visitorDAO.read(visitorID);
        } catch (DataAccessException e) {
            log.warn ("Visitor not found [{} - {}]", e.getClass().getSimpleName(), e.getMessage());
            return Optional.empty();
        }
    }



    public Optional<Person> getPersonById (long visitorID) {
        try {
            return  personDAO.read(visitorID);
        } catch (DataAccessException e) {
            log.warn ("Person nof found for visitorID [{} - {}]", e.getClass().getSimpleName(), e.getMessage());
            return Optional.empty();
        }
    }
}
