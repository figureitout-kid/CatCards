package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CatCard;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public CatCard create(@RequestBody CatCard catCard){
        return catCardDao.createCatCard(catCard);
    }

    @RequestMapping(path = "api/cards/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable int id) {
        CatCard catCard = catCardDao.getCatCardById(id);
        if (catCard == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        } else {
            return catCardDao.getCatCardById(id);
        }
    }

    @RequestMapping(path = "api/cards", method = RequestMethod.GET)
    public List<CatCard> catCards() {
        return catCardDao.getCatCards();
    }

    @RequestMapping(path = "api/cards/{id}", method = RequestMethod.PUT)
    public CatCard updateCatCard(@Valid @RequestBody CatCard catCard, @PathVariable int id){
        catCard.setCatCardId(id);
        try {
            CatCard updatedCatCard = catCardDao.updateCatCard(catCard);
            return updatedCatCard;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "api/cards/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        catCardDao.deleteCatCardById(id);

    }
}
