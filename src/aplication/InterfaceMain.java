package aplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

	
import entities.AluguelCarro;
import entities.Veiculo;
import model.service.BrasilServicoTaxa;
import model.service.RentalService;

public class InterfaceMain {
	// O que é interface?
	/*
	 * 1- Interface é um tipo que define um conjunto de operações que uma classe
	 * deve implementar. 2- A interface estabele um contrato que a classe deve
	 * cumprir
	 */

	// Para que interfaces?
	// 1- Para criar sistemas com baixo acomplamento e flexiveis

	// Situação problema:
	/*
	 * Uma locadora brasileira de carros cobra um valor por hora para locações de
	 * até 12 horas. Porém, se a duração da locação ultrapassar 12 horas, a locação
	 * será cobrada com base em um valor diário. Além do valor da locação, é
	 * acrescido no preço o valor do imposto conforme regras do país que, no caso do
	 * Brasil, é 20% para valor até 100.00. Faz um programa que lê os dados da
	 * locação (modelo do carro, instante inicial e final da locação), bem como
	 * valor por hora e o valor diário de locação. O programa deve então gerar a
	 * nota de pagamento (contendo valor da locação, valor do imposto e valor total
	 * do pagamento) e informar os dados na tela. Veja os exemplos.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //Formatação para instanciar LocalDateTime
		
		//Inserindo valores da classe aluguel
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String modeloCarro = sc.nextLine();
		System.out.print("Data de retirada: (dd/MM/yyy hh:mm)");
		LocalDateTime retirada = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Data de retorno: (dd/MM/yyy hh:mm)");
		LocalDateTime retorno =  LocalDateTime.parse(sc.nextLine(), fmt);
		
		AluguelCarro aluguel = new AluguelCarro(retirada, retorno, new Veiculo(modeloCarro));
		
		//inserindo valores da classe retalService
		
		System.out.println("Entre com o preço por hora: ");
		double precoHora = sc.nextDouble();
		System.out.println("Entre com o preço por dia: ");
		double precoDia = sc.nextDouble();
		
		RentalService retalService = new RentalService(precoHora, precoDia, new BrasilServicoTaxa());
		
		retalService.processamentoFatura(aluguel);
		
		//exibição na tela
		System.out.println("Fatura: ");
		System.out.println("Pagamento basico: " + String.format("%.2f",  aluguel.getFatura().getPagamentoBasico()));
		System.out.println("Imposto: " + String.format("%.2f", aluguel.getFatura().getTaxa()));
		System.out.println("Pagamento total: " + String.format("%.2f",  aluguel.getFatura().getPagamentoTotal()));
		sc.close();
		
		 
		

	}

}
