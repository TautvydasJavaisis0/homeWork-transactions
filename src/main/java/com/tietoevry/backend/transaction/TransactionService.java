package com.tietoevry.backend.transaction;

import com.tietoevry.backend.account.AccountRepository;
import com.tietoevry.backend.account.model.Account;
import com.tietoevry.backend.transaction.model.Transaction;
import com.tietoevry.backend.transaction.model.TransactionCreationDto;
import com.tietoevry.backend.transaction.model.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionDto createTransaction(TransactionCreationDto transactionCreationDto){

        if(transactionCreationDto.getText() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Text is empty");
        Account sender = accountRepository.getAccountByNumber(transactionCreationDto.getSenderNumber());
        if(sender == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender account does not exists");
        Account receiver = accountRepository.getAccountByNumber(transactionCreationDto.getReceiverNumber());
        if(receiver == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiver account does not exists");

        if(sender.getBalance() < transactionCreationDto.getAmount())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount to send is bigger than account balance");

        Transaction transaction =  Transaction.builder()
                .amount(transactionCreationDto.getAmount())
                .text(transactionCreationDto.getText())
                .creationDate(new Date())
                .sender(sender)
                .receiver(receiver)
                .build();

        sender.setBalance(sender.getBalance()-transactionCreationDto.getAmount());
        receiver.setBalance(receiver.getBalance()+transactionCreationDto.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        transaction = transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDto(transaction);
    }

    public List<TransactionDto> getAllTransactionByAccount(Long accountID){

        List<Transaction> transactions = transactionRepository.getAllAccountTransaction(accountID);

        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for(Transaction transaction: transactions){
            transactionDtoList.add(TransactionMapper.toTransactionDto(transaction));
        }

        return transactionDtoList;
    }

    public List<TransactionDto> getAllSendTransactionByAccount(Long accountID){

        List<Transaction> transactions = transactionRepository.getAllSendTransaction(accountID);

        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for(Transaction transaction: transactions){
            transactionDtoList.add(TransactionMapper.toTransactionDto(transaction));
        }

        return transactionDtoList;
    }

    public List<TransactionDto> getAllReceivedTransactionByAccount(Long accountID){

        List<Transaction> transactions = transactionRepository.getAllReceivedTransaction(accountID);

        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for(Transaction transaction: transactions){
            transactionDtoList.add(TransactionMapper.toTransactionDto(transaction));
        }

        return transactionDtoList;
    }
/*
    private final TransactionRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserService userService;
    private final AccountService accountService;

    public List<QuestionDto> getCommentsByInitiative(Long initiativeID){
        Account account = accountService.getInitiative(initiativeID);
        List<Question> questions = questionRepository.getAllByInitiativeOrderByCreationDateDesc(account);
        List<QuestionDto> commentDtoList = new ArrayList<>();

        for(Question question: questions){
            commentDtoList.add(TransactionMapper.toQuestionDto(question));
        }

        return commentDtoList;
    }
    public AnswerDto createAnswer(TransactionDto commentCreationDto){

        Account account = accountService.getInitiative(commentCreationDto.getInitiativeID());
        User user = userService.getLoggedInUser();

        Optional<Question> question = questionRepository.findById(commentCreationDto.getLinkedCommentID());
        question.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "question comment not found by id " + commentCreationDto.getLinkedCommentID()));

        if(ValidationUtil.doesAnswerExists(question.get())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "answer already exists");}

        if(!ValidationUtil.isOwner(account, user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "you are not a owner");}

        if(!ValidationUtil.isSameInitiative(account, question.get().getAccount())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "questions are not in same initiative");}

        Answer savedAnswer =  answerRepository
            .save(TransactionMapper.toAnswer(commentCreationDto, question.get(), user, account));
        question.get().setAnswer(savedAnswer);
        questionRepository.save(question.get());
        return TransactionMapper.toAnswerDto(savedAnswer);
    }

    public QuestionDto createQuestion(QuestionCreationDto commentCreationDto){

        Account account = accountService.getInitiative(commentCreationDto.getInitiativeID());
        User user = userService.getLoggedInUser();

        Question savedQuestion =  questionRepository
            .save(TransactionMapper.toQuestion(commentCreationDto, null, user, account));
        return TransactionMapper.toQuestionDto(savedQuestion);
    }

    public void deleteQuestion(Long id) {
        Transaction comment = commentRepository.getById(id);
        User owner = userService.getLoggedInUser();

        if(ValidationUtil.isOwner(comment.getAccount(), owner)){
            commentRepository.deleteById(id);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "you are not a owner");
    }

    public void deleteAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        answer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "answer not found by id " + answer.get().getId()));

        User owner = userService.getLoggedInUser();

        Optional<Question> question = questionRepository.getByAnswer(answer.get());
        question.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "question not found by id " + question.get().getAnswer().getId()));


        if(ValidationUtil.isOwner(answer.get().getAccount(), owner)){
            question.get().setAnswer(null);
            questionRepository.save(question.get());
            answerRepository.deleteById(id);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "you are not a owner");
    }
 */
}
