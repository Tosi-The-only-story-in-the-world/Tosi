package com.ssafy.tosi.favorite;

import com.ssafy.tosi.tale.TaleDto;
import com.ssafy.tosi.taleDetail.TaleDetailService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/favorites")
@RestController
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final TaleDetailService taleDetailService;

    @PostMapping
    public ResponseEntity<?> postFavorite(@RequestBody Favorite favorite) {
        try {
            Favorite savedFavorite = favoriteService.insertFavorite(favorite);
            boolean result = taleDetailService.updateLikeCnt(favorite.getTaleId());

            Map<Favorite, Boolean> map = new HashMap<>();
            map.put(savedFavorite, result);
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{userId}/{taleId}")
    public ResponseEntity<?> getFavorite(@PathVariable int userId, @PathVariable int taleId) {
        try {
            int result = favoriteService.getFavorite(userId, taleId);
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable int favoriteId) {
        try {
            favoriteService.deleteFavorite(favoriteId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getFavoritesList(@PathVariable int userId) {
        try {
            List<TaleDto> favoriteList = favoriteService.getFavoriteList(userId);
            return new ResponseEntity<List<TaleDto>>(favoriteList, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
