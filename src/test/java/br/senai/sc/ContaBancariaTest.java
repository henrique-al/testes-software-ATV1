package br.senai.sc;

import br.senai.sc.model.banco.ContaBancaria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContaBancariaTest {

    private ContaBancaria contaBancaria;

    @BeforeAll
    public void setUp(){
        contaBancaria = new ContaBancaria("123", "Fulano");
    }

    @BeforeEach
    public void reset(){
        contaBancaria.setTaxaJuros(0);
        contaBancaria.sacar(contaBancaria.getSaldo());
        contaBancaria.depositar(200);
    }

    @Test
    public void saldoTest(){
        assertEquals(200, contaBancaria.getSaldo());
        assertNotEquals(200.1, contaBancaria.getSaldo());
    }

    @Test
    public void numeroContaTest(){
        assertNotEquals("12³", contaBancaria.getNumeroConta());
        assertEquals("123", contaBancaria.getNumeroConta());
    }

    @Test
    public void depositarTest(){
        assertFalse(contaBancaria.depositar(-1));
        assertFalse(contaBancaria.depositar(0));
        assertTrue(contaBancaria.depositar(100));
        assertTrue(contaBancaria.depositar(0.001));
    }

    @Test
    public void sacarTest(){
        assertFalse(contaBancaria.sacar(-1));
        assertFalse(contaBancaria.sacar(0));
        assertFalse(contaBancaria.sacar(1000000));
        assertTrue(contaBancaria.sacar(10));
        assertEquals(190, contaBancaria.getSaldo());
    }

    @Test
    public void transferirTest(){
        ContaBancaria contaDestino = new ContaBancaria("456", "Ciclano");
        assertTrue(contaBancaria.transferir(contaDestino, 100));
        assertFalse(contaBancaria.transferir(contaDestino, 1000));
        assertEquals(100, contaDestino.getSaldo());
        assertEquals(100, contaBancaria.getSaldo());
    }

    @Test
    public void getAndSetTaxaJurosTest(){
        assertEquals(0, contaBancaria.getTaxaJuros());
        contaBancaria.setTaxaJuros(0.1);
        assertEquals(0.1, contaBancaria.getTaxaJuros());
    }

    @Test
    public void aplicarJurosTest(){
        contaBancaria.setTaxaJuros(0.1);
        contaBancaria.aplicarJuros();
        assertEquals(220, contaBancaria.getSaldo());
        contaBancaria.aplicarJuros();
        assertNotEquals(230, contaBancaria.getSaldo());
    }

    @Test
    public void getTitularTest(){
        assertEquals("Fulano", contaBancaria.getTitular());
    }

    @Test
    public void alterarTitularTest(){
        assertFalse(contaBancaria.alterarTitular(""));
        assertFalse(contaBancaria.alterarTitular(null));
        contaBancaria.alterarTitular("Beltrano");
        assertEquals("Beltrano", contaBancaria.getTitular());
    }
}
