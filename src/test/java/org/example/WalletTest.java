package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WalletTest {
    private Wallet senderWalletYen;
    private Wallet recipientWalletYen;
    private Wallet recipientWalletDollar;
    private final double INITIAL_BALANCE = 100;
    private final String INITIAL_CURRENCY_YEN = "Yen";
    private final String INITIAL_CURRENCY_DOLLAR = "Dollar";
    private IllegalArgumentException illegalArgumentException;

    @BeforeEach
    void init() {
        senderWalletYen = new Wallet(INITIAL_BALANCE, INITIAL_CURRENCY_YEN);
        recipientWalletYen = new Wallet(INITIAL_BALANCE, INITIAL_CURRENCY_YEN);
        recipientWalletDollar = new Wallet(INITIAL_BALANCE, INITIAL_CURRENCY_DOLLAR);
        illegalArgumentException = null;
    }
    @AfterEach
    void clear() {
        senderWalletYen = null;
        recipientWalletYen = null;
        recipientWalletDollar = null;
        illegalArgumentException = null;
    }

    @Test
    void testGetBalance() {
        assertEquals(INITIAL_BALANCE, senderWalletYen.getBalance());
    }
    @Test
    void testGetCurrency() {
        assertEquals(INITIAL_CURRENCY_YEN, senderWalletYen.getCurrency());
    }
    @Test
    void testDepositAmountPositiveAmount() {
        senderWalletYen.depositAmount(100);
        assertEquals(200, senderWalletYen.getBalance());
    }
    @Test
    void testDepositAmountNegativeAmount() {
        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            senderWalletYen.depositAmount(-100);
        });
        assertEquals(100, senderWalletYen.getBalance());
    }
    @Test
    void testDepositAmountZeroAmount() {
        senderWalletYen.depositAmount(0);
        assertEquals(100, senderWalletYen.getBalance());
    }
    @Test
    void testWithdrawAmountPositiveAmount() {
        boolean isWithdrawSuccess = senderWalletYen.withdrawAmount(25);
        assertAll(
                () -> assertTrue(isWithdrawSuccess),
                () -> assertEquals(75, senderWalletYen.getBalance())
        );
    }
    @Test
    void testWithdrawAmountNegativeAmount() {
        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            senderWalletYen.withdrawAmount(-50);
        });
        assertAll(
                () -> assertEquals("Amount cannot be negative", illegalArgumentException.getMessage()),
                () -> assertEquals(100, senderWalletYen.getBalance())
        );
    }
    @Test
    void testWithdrawAmountZeroAmount() {
        boolean isWithdrawSuccess = senderWalletYen.withdrawAmount(0);
        assertAll(
                () -> assertTrue(isWithdrawSuccess),
                () -> assertEquals(100, senderWalletYen.getBalance())
        );
    }
    @Test
    void testWithdrawAmountMoreThanBalance() {
        boolean isWithdrawSuccess = senderWalletYen.withdrawAmount(200);
        assertAll(
                () -> assertFalse(isWithdrawSuccess),
                () -> assertEquals(100, senderWalletYen.getBalance())
        );
    }

    @Test
    void testTransferFundsSufficientFundsSameCurrency() {
        senderWalletYen.transferFunds(recipientWalletYen, 30);
        assertAll(
                () -> assertEquals(70, senderWalletYen.getBalance()),
                () -> assertEquals(130, recipientWalletYen.getBalance())
        );
    }
    @Test
    void transferFundsSufficientFundsDifferentCurrency() {
        assertEquals(100, senderWalletYen.getBalance());
        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            senderWalletYen.transferFunds(recipientWalletDollar, 30);
        });
        assertAll(
                () -> assertEquals("Cannot transfer funds between different currencies", illegalArgumentException.getMessage()),
                () -> assertEquals(100, senderWalletYen.getBalance()),
                () -> assertEquals(100, recipientWalletDollar.getBalance())
        );
    }
    @Test
    void testTransferFundsInsufficientFundsSameCurrency() {
        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            senderWalletYen.transferFunds(recipientWalletYen, 150);
        });
        assertAll(
                () -> assertEquals("Insufficient funds for transfer", illegalArgumentException.getMessage()),
                () -> assertEquals(100, senderWalletYen.getBalance()),
                () -> assertEquals(100, recipientWalletYen.getBalance())
        );
    }
    @Test
    void transferFundsInsufficientFundsDifferentCurrency() {
        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            senderWalletYen.transferFunds(recipientWalletDollar, 150);
        });
        assertAll(
                () -> assertEquals("Insufficient funds for transfer", illegalArgumentException.getMessage()),
                () -> assertEquals(100, senderWalletYen.getBalance()),
                () -> assertEquals(100, recipientWalletDollar.getBalance())
        );
    }
}