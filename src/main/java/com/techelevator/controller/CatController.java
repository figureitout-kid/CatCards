package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
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

    @GetMapping("/random")
    public CatCard getRandomCatCard() {
        String catFact = catFactService.getFact().getText();
        String catPicUrl = catPicService.getPic().getFile();

        CatCard randomCatCard = new CatCard();
        randomCatCard.setCatFact(catFact);
        randomCatCard.setImgUrl(catPicUrl);
        randomCatCard.setCaption("CAT you believe it!");
        return catCardDao.createCatCard(randomCatCard);
    }


    @GetMapping
    public List<CatCard> listAllCards(){
        return catCardDao.getCatCards();
    }

    @GetMapping("{id}")
    public CatCard getCatCardById(@PathVariable int id) {
        CatCard catCard = catCardDao.getCatCardById(id);
        if (catCard == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        } else {
            return catCardDao.getCatCardById(id);
        }
    }

    @PostMapping
    public CatCard addNewCard(@RequestBody CatCard catCard) {
        return catCardDao.createCatCard(catCard);
    }

    @PutMapping("/{id}")
    public CatCard updateCard(@PathVariable int id, @RequestBody CatCard catCard) {
        catCard.setCatCardId(id);
        try {
            CatCard updatedCatCard = catCardDao.updateCatCard(catCard);
            return updatedCatCard;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable int id) {
        catCardDao.deleteCatCardById(id);
    }
}
