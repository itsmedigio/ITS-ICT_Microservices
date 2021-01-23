package org.digiovanni.borrow.controllers;

import lombok.extern.slf4j.Slf4j;
import org.digiovanni.borrow.models.Borrow;
import org.digiovanni.borrow.repos.BorrowRepository;
import org.digiovanni.borrow.services.NotificationClient;
import org.digiovanni.borrow.services.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/v2/borrow")
public class BorrowController {

    @Autowired
    private final BorrowRepository borrowRepository;
    @Autowired
    TraceService traceService;
    @Autowired
    NotificationClient notificationClient;
    @Value("${kafka.sms.message}")
    private String message;

    public BorrowController(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    // default action: get all borrows
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Borrow> getAllBorrows() {
        List<Borrow> result = new ArrayList<Borrow>();
        Iterable<Borrow> iterable = borrowRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }

    // add one borrow put with ID
    @RequestMapping(method = RequestMethod.PUT)
    public Borrow addNewBorrow(@RequestBody Borrow borrow) {
        notificationClient.sendSMS(borrow);
        return borrowRepository.save(borrow);
    }

    // get a borrow with its id
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.GET)
    public Borrow getBorrow(@PathVariable String borrowId) {
        Optional<Borrow> borrowOptional = borrowRepository.findById(borrowId);
        if (borrowOptional.isPresent()) {
            return borrowOptional.get();
        } else {
            return null;
        }
    }

    // edit borrow
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.POST)
    public Borrow modifyBorrow(@RequestBody Borrow borrow, @RequestBody String borrowId) {
        return borrowRepository.save(borrow);
    }

    // DELETE ALL BORROWS
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBorrows() {
        borrowRepository.deleteAll();
    }

    // delete borrow by id
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.DELETE)
    public void deleteBorrow(@PathVariable String borrowId) {
        borrowRepository.deleteById(borrowId);
    }

}
