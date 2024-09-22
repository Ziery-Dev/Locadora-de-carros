package model.service;

import java.time.Duration;

import entities.AluguelCarro;
import entities.Fatura;

public class RentalService { //essa classe recebe os atributos dos preços por hora e dia, alem de possuir a função que faz o chamado para exibir a fatura
	private Double precoPorHora;
	private Double precoPorDia;
	
	private TaxService taxService;

	public RentalService(Double precoPorHora, Double precoPorDia, TaxService taxService) {
		this.precoPorHora = precoPorHora;
		this.precoPorDia = precoPorDia;
		this.taxService = taxService;
	}


	
	//essa classe recebe os dados do aluguel e calcula o espaço de tempo do inicio e fim do alguel, pegando os dados em minutos, para que caso o tempo de horas e mais alguns minutos, possa ser arrendondado para a hora seguinte
	public void processamentoFatura(AluguelCarro aluguel) {
		double minutos = Duration.between(aluguel.getInicio(), aluguel.getFim()).toMinutes();
		double horas = minutos / 60;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico =  precoPorHora * Math.ceil(horas);
		}
		else {
			pagamentoBasico = precoPorDia * Math.ceil(horas/24);
		}
		
		double taxa = taxService.taxa(pagamentoBasico);
		
		aluguel.setFatura(new Fatura(pagamentoBasico, taxa));
	}
}

