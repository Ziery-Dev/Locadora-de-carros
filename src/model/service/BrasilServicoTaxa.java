package model.service;

public class BrasilServicoTaxa implements TaxService{
	

	//classe responsavel para calcular a taxa de juros Brasileiro de acordo com o custo do aluguel
	public double taxa(double valor) {
		if(valor <= 100) {
			return valor * 0.2;
		}
		else {
			return valor * 0.15;
		}
	}
	
}
