package com.hps.controller;

import com.hps.dto.CountRange;
import com.hps.luhn.Luhn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class LuhnController {

    Luhn luhn;

    LuhnController() {
        luhn = new Luhn();
    }

    @PostMapping("/luhn")
    public int generateCheckDigit(@RequestParam String cardNumber) {
        return luhn.generateCheckDigit(new BigInteger(cardNumber));
    }

    @GetMapping("/luhn/{startRange}/{endRange}/count")
    public CountRange countRange(@PathVariable("startRange") String startRange, @PathVariable("endRange") String endRange) {
        BigInteger start = new BigInteger(startRange);
        BigInteger end = new BigInteger(endRange);
        BigInteger runner = start;
        BigInteger startNumber = BigInteger.ZERO;
        BigInteger endNumber = BigInteger.ZERO;
        int count = 0;

        while (runner.compareTo(end) <= 0) {
            if (luhn.isValidLuhn(runner)) {
                count += 1;
                endNumber = runner;
                if (startNumber.compareTo(BigInteger.ZERO) == 0) {
                    startNumber = runner;
                }
            }
            runner = runner.add(BigInteger.ONE);
        }
        return new CountRange(startNumber.toString(), endNumber.toString(), count);
    }

    @GetMapping("/luhn/{cardNumber}")
    public ResponseEntity isValidLuhn(@PathVariable("cardNumber") String cardNumber) {
        BigInteger curChecking = new BigInteger(cardNumber);
        if (curChecking.compareTo(BigInteger.ZERO) <= 0)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else if (luhn.isValidLuhn(curChecking))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}