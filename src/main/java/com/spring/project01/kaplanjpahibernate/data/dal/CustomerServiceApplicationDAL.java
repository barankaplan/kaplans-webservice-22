package com.spring.project01.kaplanjpahibernate.data.dal;


import com.spring.project01.kaplanjpahibernate.data.entity.*;
import com.spring.project01.kaplanjpahibernate.data.repository.*;
import com.spring.project01.kaplanjpahibernate.geoname.json.dto.PostalCodeGeoNames;
import com.spring.project01.kaplanjpahibernate.mapper.IPostalCodeMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForRepositoryRunnable;


@Component
public class CustomerServiceApplicationDAL {

    private final ICustomerRepository iCustomerRepository;
    private final IBookRepository iBookRepository;
    private final ICardRepository iCardRepository;
    private final IPostalCodeRepository iPostalCodeRepository;
    private final IPostalCodeInfoRepository iPostalCodeInfoRepository;
    private final RestTemplate restTemplate;
    private final IPostalCodeMapper iPostalCodeMapper;

    private final IMovieRepository movieRepository;
    private final IMovieInformationRepository iMovieInformationRepository;


    public CustomerServiceApplicationDAL(ICustomerRepository iCustomerRepository, IBookRepository iBookRepository, ICardRepository iCardRepository, IPostalCodeRepository iPostalCodeRepository, IPostalCodeInfoRepository iPostalCodeInfoRepository, RestTemplate restTemplate, IPostalCodeMapper iPostalCodeMapper, IMovieRepository movieRepository, IMovieInformationRepository iMovieInformationRepository) {
        this.iCustomerRepository = iCustomerRepository;
        this.iBookRepository = iBookRepository;
        this.iCardRepository = iCardRepository;
        this.iPostalCodeRepository = iPostalCodeRepository;
        this.iPostalCodeInfoRepository = iPostalCodeInfoRepository;
        this.restTemplate = restTemplate;
        this.iPostalCodeMapper = iPostalCodeMapper;
        this.movieRepository = movieRepository;
        this.iMovieInformationRepository = iMovieInformationRepository;
    }


    public Customer mapCustomerAndBookAndCardAndMovie(Customer customer) {
        if (customer.getCustomerId() == null) {
            Customer theCustomer = new Customer();
            fillTheBooks(customer, theCustomer);
            fillTheCards(customer, theCustomer);
            fillTheMovies(customer, theCustomer);
            return doWorkForRepository(() -> iCustomerRepository.save(theCustomer), "CustomerServiceApplicationDAL.mapCustomerAndBook");
        }
        return null;
    }

    public Customer mapCustomerAndBookTested(Customer customer) {
//        Optional<Customer> byCustomerId = iCustomerRepository.findByCustomerId(customer.getCustomerId());
//        if (byCustomerId.isEmpty()) {
//            System.out.println("exception!");
//            throw new NoSuchElementException();
//        }
        Customer theCustomer = new Customer();
//        theCustomer.setCustomerId(2L);
        fillTheBooksTest(customer, theCustomer);
        iCustomerRepository.save(theCustomer);
        return theCustomer;

//        return iCustomerRepository.save(theCustomer);


    }

    public void mapCustomerAndBookTestedv2(Customer customer) {

        Optional<Customer> byCustomerId = iCustomerRepository.findByCustomerId(customer.getCustomerId());

        if (byCustomerId.get().getCustomerId() == null) {
            System.out.println("exception!");
            throw new NullPointerException();
        }
    }


    public void mapCustomerAndBookTestedv3() {

          iCustomerRepository.findAll();

//        if (byCustomerId.get().getCustomerId() == null) {
//            System.out.println("exception!");
//            throw new NullPointerException();
//        }
    }


    public Customer mapCustomerAndBookV1(Customer customer) {

        Optional<Customer> optionalCustomerCustomer = iCustomerRepository.findById(customer.getCustomerId());

        if (optionalCustomerCustomer.isEmpty()) {
            Customer theCustomer = new Customer();
            fillTheBooks(customer, theCustomer);
            fillTheCards(customer, theCustomer);
            fillTheMovies(customer, theCustomer);
            return doWorkForRepository(() -> iCustomerRepository.save(theCustomer), "CustomerServiceApplicationDAL.mapCustomerAndBook");
        }

        fillTheBooks(customer, optionalCustomerCustomer.get());
        fillTheCards(customer, optionalCustomerCustomer.get());
        fillTheMovies(customer, optionalCustomerCustomer.get());
        return doWorkForRepository(() -> iCustomerRepository.save(optionalCustomerCustomer.get()), "CustomerServiceApplicationDAL.mapCustomerAndBook");
    }

    public Customer mapCustomerAndBookV2(Customer customer) {

        Optional<Customer> optionalCustomerCustomer = iCustomerRepository.findById(customer.getCustomerId());

        if (optionalCustomerCustomer.isEmpty()) {
            Customer theCustomer = new Customer();
            fillTheBooks(customer, theCustomer);
            fillTheCards(customer, theCustomer);
            return doWorkForRepository(() -> iCustomerRepository.save(theCustomer), "CustomerServiceApplicationDAL.mapCustomerAndBook");
        }

        fillTheBooks2(customer, optionalCustomerCustomer.get());
        fillTheCards2(customer, optionalCustomerCustomer.get());
        fillTheMovies2(customer, optionalCustomerCustomer.get());

        return doWorkForRepository(() -> iCustomerRepository.save(optionalCustomerCustomer.get()), "CustomerServiceApplicationDAL.mapCustomerAndBook");
    }


    private void fillTheCards(Customer customer, Customer theCustomer) {
        theCustomer.setBankCards(customer.getBankCards().
                stream()
                .map(c -> {
                    Optional<BankCard> optionalBankCard = iCardRepository.findById(c.getCardId());
                    if (optionalBankCard.isEmpty()) {
                        BankCard bankCard = new BankCard();
                        bankCard.setCardId(c.getCardId());
                        bankCard.setContactlessPayment(c.isContactlessPayment());
                        bankCard.setCustomer(theCustomer);
                        iCardRepository.save(bankCard);
                        return bankCard;
                    }
                    return null;

                }).collect(Collectors.toList()));
    }

    private void fillTheCards2(Customer customer, Customer theCustomer) {
        theCustomer.getBankCards().clear();

        theCustomer.setBankCards(customer.getBankCards().
                stream()
                .map(c -> {
                    Optional<BankCard> optionalBankCard = iCardRepository.findById(c.getCardId());
                    if (optionalBankCard.isEmpty()) {
                        BankCard bankCard = new BankCard();
                        bankCard.setCardId(c.getCardId());
                        bankCard.setContactlessPayment(c.isContactlessPayment());
                        bankCard.setCustomer(theCustomer);
                        iCardRepository.save(bankCard);
                        return bankCard;
                    } else {
                        optionalBankCard.get().setCardId(c.getCardId());
                        optionalBankCard.get().setContactlessPayment(c.isContactlessPayment());
                        optionalBankCard.get().setCustomer(theCustomer);
                        return optionalBankCard.get();
                    }
                }).collect(Collectors.toList()));

        List<Long> values =
                theCustomer.getBankCards().stream()
                        .map(BankCard::getCardId)
                        .collect(Collectors.toList());

        iCustomerRepository.updateBankCard(theCustomer.getCustomerId(), values);


    }


    private void fillTheBooks(Customer customer, Customer theCustomer) {
        theCustomer.setFirstName(customer.getFirstName());
        theCustomer.getBooks()
                .addAll(customer
                        .getBooks()
                        .stream()
                        .map(b -> {
                            Book book = iBookRepository.findByBookId(b.getBookId());
                            book.getCustomers().add(theCustomer);
                            return book;
                        }).collect(Collectors.toSet()));
    }

    private void fillTheBooksTest(Customer customer, Customer theCustomer) {
        theCustomer.setFirstName(customer.getFirstName());
        theCustomer.getBooks()
                .addAll(customer
                        .getBooks()
                        .stream()
                        .map(b -> {
                            Optional<Book> book = iBookRepository.findByBookIdTest(b.getBookId());

                            book.get().getCustomers().add(theCustomer);
                            return book.get();

                        }).collect(Collectors.toSet()));
    }

    private void fillTheMovies(Customer customer, Customer theCustomer) {

        theCustomer.getMovieInformations()
                .addAll(customer
                        .getMovieInformations()
                        .stream()
                        .map(b -> {
                            MovieInformation movieInformation = iMovieInformationRepository.findByInfoId(b.getInfoId());
                            movieInformation.getCustomers().add(theCustomer);
                            return movieInformation;
                        }).collect(Collectors.toSet()));
    }

    void fillTheBooks2(Customer customer, Customer theCustomer) {
        theCustomer.getBooks().clear();
        theCustomer.setFirstName(customer.getFirstName());
        theCustomer.getBooks()
                .addAll(customer
                        .getBooks()
                        .stream()
                        .map(b -> {
                            Book book = iBookRepository.findByBookId(b.getBookId());
                            book.getCustomers().add(theCustomer);
                            return book;
                        }).collect(Collectors.toSet()));

        List<Long> values =
                theCustomer.getBooks().stream()
                        .map(Book::getBookId)
                        .collect(Collectors.toList());

        iCustomerRepository.updateJoinTableCustomerBook(theCustomer.getCustomerId(), values);
    }

    void fillTheMovies2(Customer customer, Customer theCustomer) {
        theCustomer.getMovieInformations().clear();
        theCustomer.setFirstName(customer.getFirstName());
        theCustomer.getMovieInformations()
                .addAll(customer
                        .getMovieInformations()
                        .stream()
                        .map(b -> {
                            MovieInformation movieInformation = iMovieInformationRepository.findByInfoId(b.getInfoId());
                            movieInformation.getCustomers().add(theCustomer);
                            return movieInformation;
                        }).collect(Collectors.toSet()));

        List<Long> values =
                theCustomer.getMovieInformations().stream()
                        .map(MovieInformation::getInfoId)
                        .collect(Collectors.toList());

        iCustomerRepository.updateJoinTableCustomerMovie(theCustomer.getCustomerId(), values);
    }


    private PostalCode savePostalCodeCallBack(PostalCode postalCode) {

        var theCustomer = iCustomerRepository.findById(postalCode.customerId);
        if (theCustomer.isEmpty())
            throw new IllegalArgumentException("Invalid CustomerId or PostalCode");
        var thePostalCode = iPostalCodeRepository.findByCustomerId(postalCode.getCustomerId());
        if (thePostalCode.isPresent()) {
            System.out.println("we know that,there is an existing customerId associated with the postalCode sent by postman" +
                    "We will now update the existing zip code of this current customer." +
                    "If the postal code to be updated exists in the table named postal_codes_info, it will be taken directly" +
                    " from this table and this table will not have to talk to the service." +
                    "In this way, the query will return much faster, in fact," +
                    " this is one of the most basic cache systems."
            );
            thePostalCode.get().setPostalCode(postalCode.getPostalCode());
            thePostalCode.get().setPostalCodeInfos(postalCode.getPostalCodeInfos());
            thePostalCode.get().setCustomerId(postalCode.getCustomerId());
            findPostalCodeInfoByPostalCode(thePostalCode.get());
            return doWorkForRepository(() -> iPostalCodeRepository.save(thePostalCode.get()), "CustomerServiceApplicationDAL.savePostalCodeCallBack");
        } else {
            postalCode.customer = theCustomer.get();
            findPostalCodeInfoByPostalCode(postalCode);
            return iPostalCodeRepository.save(postalCode);
        }
    }


    public void findPostalCodeInfoByPostalCode(PostalCode postalCode) {
        if (existsPostalCodeById(postalCode.getPostalCode())) {
            getPostalCodesViaDatabase(postalCode);
        } else {
            getAndSavePostalCodesViaService(postalCode);
        }
    }

    public boolean existsPostalCodeById(long postalCode) {
        return iPostalCodeInfoRepository.existsByPostalcode(Integer.toString((int) postalCode));
    }


    public void getPostalCodesViaDatabase(PostalCode postalCode) {
        System.out.println("postal codes are calling via database, no need to take an information from the service");
        postalCode.postalCodeInfos = iPostalCodeInfoRepository.findByPostalCode(Integer.toString(postalCode.getPostalCode()));
    }


    @Transactional
    public PostalCode mapCustomerAndPostalCode(PostalCode postalCode) {
        return doWorkForRepository(() -> savePostalCodeCallBack(postalCode), "CustomerServiceApplicationDAL.mapCustomerAndPostalCode");
    }


    public void getAndSavePostalCodesViaService(PostalCode pc) {
        System.out.println("postal codes are calling by interacting with service");
        doWorkForRepositoryRunnable(() -> connectToTheService(pc), "CustomerServiceApplicationDAL.mapCustomerAndPostalCode");
    }

    private void connectToTheService(PostalCode pc) {
        var url = String.format(m_urlTemplate, pc.getPostalCode());
        var postalCodeInfoGeoNames = Objects.requireNonNull(restTemplate.getForObject(url, PostalCodeGeoNames.class), "Geonames get errors")
                .postalCodeGeoNames;
        postalCodeInfoGeoNames.stream().map(iPostalCodeMapper::toPostalCodeInfo)
                .findFirst().ifPresent(pci -> getAndSavePostalCodesForEachCallback(pci, pc));
    }


    public void getAndSavePostalCodesForEachCallback(PostalCodeInfo postalCodeInfo, PostalCode postalCode) {
        postalCode.postalCodeInfos = postalCodeInfo;
    }

    @Value("${geonames.uri}")
    private String m_urlTemplate;


//    public Customer mapCustomerAndBook(Customer customer) {
//        Optional<Customer> theCustomer = iCustomerRepository.findById(customer.getCustomerId());
////        theCustomer.get().setBooks2(customer.getBooks());
//        iCustomerRepository.save(theCustomer.get());
//        return theCustomer.get();
////        return theCustomer.map(value -> doWorkForRepository(() -> iCustomerRepository.save(value), "CustomerServiceApplicationDAL.mapCustomerAndBook")).orElse(null);
//
//    }
}
