package br.senai.sc.model.banco;

public class ContaBancaria {

    private String numeroConta;
    private String titular;
    private double saldo;
    private double taxaJuros;

    public ContaBancaria(String numeroConta, String titular) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = 0;
        this.taxaJuros = 0;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(ContaBancaria contaDestino, double valor) {
        if (sacar(valor)) {
            contaDestino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public void aplicarJuros() {
        saldo += saldo * taxaJuros;
    }

    public boolean alterarTitular(String novoTitular) {
        if (novoTitular != null && !novoTitular.trim().isEmpty()) {
            titular = novoTitular;
            return true;
        } else {
            return false;
        }
    }
}
