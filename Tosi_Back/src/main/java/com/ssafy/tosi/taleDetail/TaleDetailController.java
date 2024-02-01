package com.ssafy.tosi.taleDetail;

import com.ssafy.tosi.tale.TaleDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaleDetailController {

    private final TaleDetailService taleDetailService;

    @GetMapping("/tales/{taleId}")
    public ResponseEntity<?> getTaleDetail(@PathVariable int taleId) {
        try {
            TaleDto taleDto = taleDetailService.getTaleDetail(taleId);
            return new ResponseEntity<TaleDto>(taleDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            String errMsg = "Entity Not Found";
            return new ResponseEntity<String>(errMsg, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/tales/{CName}/{BName}")
    public ResponseEntity<?> selectName(@PathVariable String CName, @PathVariable String BName) {
        try {
            taleDetailService.selectName(CName, BName);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/tales/read")
    public ResponseEntity<?> readBook(@RequestBody TaleDto taleDto) {
        try {
            String[] splitted_contents = taleDetailService.split_sentences(taleDto); // 문장 분리
            String[] changedContents = taleDetailService.changeName(splitted_contents); // 이름 바꾸기
            List<Page> pages = taleDetailService.paging(changedContents, taleDto); // 페이지 형식으로 변경
            return new ResponseEntity<List<Page>>(pages, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
